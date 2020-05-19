package com.opussoftware.service.mapper;


import com.opussoftware.domain.*;
import com.opussoftware.service.dto.BookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = {SubjectMapper.class, AuthorMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {


    @Mapping(target = "copyBooks", ignore = true)
    @Mapping(target = "removeCopyBooks", ignore = true)
    @Mapping(target = "removeSubjects", ignore = true)
    @Mapping(target = "removeAuthors", ignore = true)
    Book toEntity(BookDTO bookDTO);

    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
