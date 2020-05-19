package com.opussoftware.service.mapper;


import com.opussoftware.domain.*;
import com.opussoftware.service.dto.LoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Loan} and its DTO {@link LoanDTO}.
 */
@Mapper(componentModel = "spring", uses = {LibraryUserMapper.class, CopyBookMapper.class})
public interface LoanMapper extends EntityMapper<LoanDTO, Loan> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "copyBook.id", target = "copyBookId")
    LoanDTO toDto(Loan loan);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "copyBookId", target = "copyBook")
    Loan toEntity(LoanDTO loanDTO);

    default Loan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loan loan = new Loan();
        loan.setId(id);
        return loan;
    }
}
