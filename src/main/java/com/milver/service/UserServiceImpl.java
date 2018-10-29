package com.milver.service;

import com.milver.domain.User;
import com.milver.repository.UserRepository;
import com.milver.service.dto.UserDto;
import com.milver.service.mapper.UserMapper;
import com.milver.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private static final String ENTITY = "User";

    @Override
    public Page<UserDto> findAllEntities(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> userMapper.userToUserDto(user));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userMapper.userDtoToUser(userDto));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDto entity) {
        if (!userRepository.findById(entity.getId()).isPresent())
            throw new ResourceNotFoundException(ENTITY);
        return save(entity);
    }

    @Override
    public UserDto findEntity(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new ResourceNotFoundException(ENTITY);
        return userMapper.userToUserDto(user.get());
    }

    @Override
    public void deleteEntity(Long id) {

    }
}
