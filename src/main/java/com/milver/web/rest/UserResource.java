package com.milver.web.rest;


import com.milver.service.dto.UserDto;
import com.milver.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    })
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
