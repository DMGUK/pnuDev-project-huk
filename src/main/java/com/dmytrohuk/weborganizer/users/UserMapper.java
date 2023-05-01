package com.dmytrohuk.weborganizer.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserViewDTO toViewDTO(User user);

    UserCreateDTO toCreateDTO(User user);

    UserUpdateDTO toUpdateDTO(User user);

    User toUser(UserCreateDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUser(UserUpdateDTO updateDTO, @MappingTarget User user);
}
