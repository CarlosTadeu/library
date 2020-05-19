package com.opussoftware.service.mapper;


import com.opussoftware.domain.*;
import com.opussoftware.service.dto.CopyBookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CopyBook} and its DTO {@link CopyBookDTO}.
 */
@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface CopyBookMapper extends EntityMapper<CopyBookDTO, CopyBook> {

    @Mapping(source = "book.id", target = "bookId")
    CopyBookDTO toDto(CopyBook copyBook);

    @Mapping(source = "bookId", target = "book")
    CopyBook toEntity(CopyBookDTO copyBookDTO);

    default CopyBook fromId(Long id) {
        if (id == null) {
            return null;
        }
        CopyBook copyBook = new CopyBook();
        copyBook.setId(id);
        return copyBook;
    }
}
