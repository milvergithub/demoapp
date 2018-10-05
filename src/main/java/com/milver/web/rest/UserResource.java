package com.milver.web.rest;


import com.milver.service.dto.UserDto;
import com.milver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers(Pageable pageable) {

        Page<UserDto> page = userService.findAllEntities(pageable);
        return new ResponseEntity<Object>(page, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        UserDto userDto = userService.findEntity(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity saveUser(@Valid UserDto userDto) {
        UserDto result = userService.save(userDto);
        return ResponseEntity.created(URI.create("/v1/users/" + result.getId())).body(result);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        UserDto result = userService.update(userDto);
        return ResponseEntity.ok(result);
    }

}
