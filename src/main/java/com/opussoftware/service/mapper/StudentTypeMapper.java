package com.opussoftware.service.mapper;


import com.opussoftware.domain.*;
import com.opussoftware.service.dto.StudentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StudentType} and its DTO {@link StudentTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentTypeMapper extends EntityMapper<StudentTypeDTO, StudentType> {


    default StudentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentType studentType = new StudentType();
        studentType.setId(id);
        return studentType;
    }
}
