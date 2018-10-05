package com.milver.service.mapper;

import com.milver.domain.User;
import com.milver.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    @Mapping(
            target = "password", ignore = true
    )
    UserDto userToUserDTO(User user);

    User userDTOToUser(UserDto userDTO);
}

