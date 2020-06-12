package com.opussoftware.service.impl;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.opussoftware.domain.CopyBook;
import com.opussoftware.domain.LibraryUser;
import com.opussoftware.domain.Loan;
import com.opussoftware.repository.CopyBookRepository;
import com.opussoftware.repository.LibraryUserRepository;
import com.opussoftware.repository.LoanRepository;
import com.opussoftware.security.DomainUserDetailsService;
import com.opussoftware.security.SecurityUtils;
import com.opussoftware.service.LoanService;
import com.opussoftware.service.dto.CopyBookDTO;
import com.opussoftware.service.dto.LoanDTO;
import com.opussoftware.service.mapper.LoanMapper;
import com.opussoftware.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Loan}.
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    public static final String ENTITY_NAME = "loan";
    private final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    private final LibraryUserRepository libraryUserRepository;

    private final CopyBookRepository copyBookRepository;

    private final DomainUserDetailsService domainUserDetailsService;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper, LibraryUserRepository libraryUserRepository, CopyBookRepository copyBookRepository, DomainUserDetailsService domainUserDetailsService) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.libraryUserRepository = libraryUserRepository;
        this.copyBookRepository = copyBookRepository;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    /**
     * Create a loan.
     *
     * @param loanDTO the entity to create.
     * @return the persisted entity
     */
    @Override
    public LoanDTO create(LoanDTO loanDTO) {
        LibraryUser libraryUser = libraryUserRepository.findById(loanDTO.getUserId())
            .orElseThrow(() -> new BadRequestAlertException("Library User not found", "loan", "usernotfound"));

        CopyBook copyBook = copyBookRepository.findById(loanDTO.getCopyBookId())
            .orElseThrow(() -> new BadRequestAlertException("Copy book not found", "loan", "copybooknotfound"));
        if (!copyBook.getAvailable())
            throw new BadRequestAlertException("Copy is already on loan", "loan", "copyonloan");

        userIsValidForLoan(libraryUser);

        LocalDate dateToBeReturned = LocalDate.now().plusDays(libraryUser.getStudentType().getNumberOfDaysLoan());

        copyBook.setAvailable(false);
        Loan loan = new Loan().copyBook(copyBook).user(libraryUser).numberOfRenewals(0).
            loanDate(LocalDate.now()).dateToBeReturned(dateToBeReturned);

        loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Return a loan.
     *
     * @param copyBookDTO the copy book to return.
     * @return the entity
     */
    @Override
    public LoanDTO returnLoan(CopyBookDTO copyBookDTO) {
        CopyBook copyBook = copyBookRepository.findById(copyBookDTO.getId())
            .orElseThrow(() -> new BadRequestAlertException("Copy book not found", "loan", "copynotfound"));

        Loan loan = loanRepository.findByCopyBookAndDateReturnedIsNull(copyBook)
            .orElseThrow(() -> new BadRequestAlertException("This copy book is not on loan", "loan", "copynotonloan"));

        loan.setDateReturned(LocalDate.now());
        loan.getCopyBook().setAvailable(true);

        LibraryUser user = loan.getUser();
        if (LocalDate.now().compareTo(loan.getDateToBeReturned()) > 0) {
            LocalDate d1 = LocalDate.now();
            LocalDate d2 = loan.getDateToBeReturned();
            Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
            user.setSuspensionDate(LocalDate.now().plusDays(diff.toDays()));
        }

        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Checks if user is valid for loan
     *
     * @param libraryUser the entity to be checked.
     */
    private void userIsValidForLoan(LibraryUser libraryUser) {
        if (libraryUser.getSuspensionDate() != null) {
            if (libraryUser.getSuspensionDate().compareTo(LocalDate.now()) > 0)
                throw new BadRequestAlertException("This user is suspended", ENTITY_NAME, "usersuspended");
            else {
                libraryUser.setSuspensionDate(null);
//                libraryUserRepository.save(libraryUser);
            }
        }

        List<Loan> currentLoans = loanRepository.findByUserAndDateReturnedIsNull(libraryUser);

        if (currentLoans.size() == libraryUser.getStudentType().getMaxBooksOnLoan())
            throw new BadRequestAlertException("User reached maximum loan copies", ENTITY_NAME, "reachedmaxcopies");

        for (Loan loan : currentLoans) {
            if (loan.getDateToBeReturned().compareTo(LocalDate.now()) < 0)
                throw new BadRequestAlertException("User have copies to be returned", ENTITY_NAME, "copiestobereturned");
        }
    }

    /**
     * Renew a loan.
     *
     * @param loanDTO the id of the entity.
     * @return the entity.
     */
    @Override
    public LoanDTO renewLoan(LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(loanDTO.getId())
            .orElseThrow(() -> new BadRequestAlertException("Loan not found", ENTITY_NAME, "loannotfound"));

        LibraryUser user = loan.getUser();
        List<Loan> loans = loanRepository.findByUserAndDateReturnedIsNull(user);
        for (Loan loan1 : loans) {
            if (loan1.getDateToBeReturned().compareTo(LocalDate.now()) < 0)
                throw new BadRequestAlertException("User have copies to be returned", ENTITY_NAME, "copiestobereturned");
        }

        if (!loans.contains(loan))
            throw new BadRequestAlertException("Current loan not found", ENTITY_NAME, "loannotfound");

        Integer updateRenewals = loan.getNumberOfRenewals() + 1;
        if (updateRenewals > user.getStudentType().getMaxRenewalNumber())
            throw new BadRequestAlertException("Renewal limit reached", ENTITY_NAME, "limitrenewal");

        loan.setDateToBeReturned(LocalDate.now().plusDays(user.getStudentType().getNumberOfDaysRenewal()));
        loan.setNumberOfRenewals(updateRenewals);

        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LoanDTO save(LoanDTO loanDTO) {
        log.debug("Request to save Loan : {}", loanDTO);

        Loan loan = loanMapper.toEntity(loanDTO);
        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Get all the loans.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> findAll() {
        log.debug("Request to get all Loans");
        return loanRepository.findAll().stream()
            .map(loanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the loans by logged user.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> findAllByUser() {
        log.debug("Request to get all Loans by logged User");

       String login = SecurityUtils.getCurrentUserLogin()
           .orElseThrow(() -> new BadRequestAlertException("Invalid user", "loan", "loginnotfound"));

       LibraryUser currentUser = libraryUserRepository.findByRg(login);
       return loanRepository.findAllByUser(currentUser).stream()
           .map(loanMapper::toDto)
           .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one loan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LoanDTO> findOne(Long id) {
        log.debug("Request to get Loan : {}", id);

        if (SecurityUtils.isCurrentUserInRole("user")) {
            Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new BadRequestAlertException("Access is denied", ENTITY_NAME, "loannotfound"));

            String login = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new BadRequestAlertException("Access is denied", ENTITY_NAME, "usernotfound"));

            if (!loan.getUser().getRg().equals(login))
                throw new BadRequestAlertException("Access is denied", ENTITY_NAME, "accessdenied");
        }

        return loanRepository.findById(id)
            .map(loanMapper::toDto);
    }

    /**
     * Delete the loan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Loan : {}", id);
        loanRepository.deleteById(id);
    }
}
