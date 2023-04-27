package com.dmytrohuk.weborganizer.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserCreateDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUserThroughMapper(UserDTO userDTO, @MappingTarget User user);
}
