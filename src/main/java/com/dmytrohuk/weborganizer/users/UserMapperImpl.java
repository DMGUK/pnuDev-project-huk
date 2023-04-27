package com.dmytrohuk.weborganizer.users;

public class UserMapperImpl implements UserMapper{
    @Override
    public UserDTO toUserDTO(User user) {
        if (user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSurname(user.getSurname());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if (userDTO == null){
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setSurname(userDTO.getSurname());
        user.setAddress(userDTO.getAddress());
        return user;
    }
}
