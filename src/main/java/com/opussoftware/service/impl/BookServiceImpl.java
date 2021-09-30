package com.opussoftware.service.impl;

import com.opussoftware.domain.Author;
import com.opussoftware.domain.Book;
import com.opussoftware.repository.AuthorRepository;
import com.opussoftware.repository.BookRepository;
import com.opussoftware.repository.CopyBookRepository;
import com.opussoftware.repository.SubjectRepository;
import com.opussoftware.service.BookService;
import com.opussoftware.service.dto.BookDTO;
import com.opussoftware.service.dto.SearchDTO;
import com.opussoftware.service.mapper.BookMapper;
import com.opussoftware.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Book}.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final CopyBookRepository copyBookRepository;

    private final AuthorRepository authorRepository;

    private final SubjectRepository subjectRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, CopyBookRepository copyBookRepository, AuthorRepository authorRepository, SubjectRepository subjectRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.copyBookRepository = copyBookRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    /**
     * Create a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BookDTO create(BookDTO bookDTO) {
        log.debug("Request to create Book : {}", bookDTO);

        if (bookRepository.existsBooksByIsbn(bookDTO.getIsbn()))
            throw new BadRequestAlertException("Isbn already exists", "book", "isbnexists");

        return save(bookDTO);
    }

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    /**
     * Get all the books.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        return bookRepository.findAllWithEagerRelationships().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the books with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BookDTO> findAllWithEagerRelationships(Pageable pageable) {
        return bookRepository.findAllWithEagerRelationships(pageable).map(bookMapper::toDto);
    }

    /**
     * Get one book by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookDTO> findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findOneWithEagerRelationships(id)
            .map(bookMapper::toDto);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
    }

    /**
     * Get all the books with option search.
     *
     * @return the list of entities.
     */
    @Override
    public List<BookDTO> search(SearchDTO searchDTO) {
        List<Book> books;

        switch (searchDTO.getOption()) {
            case "Title":
                books = bookRepository.findAllByTitleContaining(searchDTO.getValue());
                break;
            case "Author":
                books = new LinkedList<>();
                List<Author> authors = authorRepository.findAllByNameContaining(searchDTO.getValue());
                for (Author author : authors)
                    books.addAll(bookRepository.findAllByAuthorsIsContaining(author));
                break;
            case "Subject":
                String subject = searchDTO.getValue().replaceAll(" ", "_").toUpperCase();
                books = bookRepository.findAllBySubjectsIsContaining(subjectRepository.findBySubjectIsLike(subject));
                break;
            case "Publisher":
                books = bookRepository.findAllByPublisherContaining(searchDTO.getValue());
                break;
            case "ISBN":
                books = bookRepository.findAllByIsbnStartingWith(searchDTO.getValue());
                break;
            default:
                throw new BadRequestAlertException("Unexpected value: " + searchDTO.getOption(), "BOOK", "unexpectedSearchOption");
        }

        List<BookDTO> dtoList = books.stream().map(bookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
        dtoList.stream().forEach(bookDTO -> {
            Book book = bookMapper.toEntity(bookDTO);
            bookDTO.setTotalCopies(copyBookRepository.findTotalCopiesByBook(book));
            bookDTO.setAvailableCopies(copyBookRepository.findAvailableCopiesByBook(book));
        });
        return dtoList;
    }
}
