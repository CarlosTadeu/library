package com.opussoftware.service.mapper;


import com.opussoftware.domain.*;
import com.opussoftware.service.dto.LibraryUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LibraryUser} and its DTO {@link LibraryUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudentTypeMapper.class})
public interface LibraryUserMapper extends EntityMapper<LibraryUserDTO, LibraryUser> {

    @Mapping(source = "studentType.id", target = "studentTypeId")
    LibraryUserDTO toDto(LibraryUser libraryUser);

    @Mapping(source = "studentTypeId", target = "studentType")
    LibraryUser toEntity(LibraryUserDTO libraryUserDTO);

    default LibraryUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setId(id);
        return libraryUser;
    }
}
