package org.example.xclone.controller;


import jakarta.validation.Valid;
import org.example.xclone.model.dto.UserDto;
import org.example.xclone.model.entity.User;
import org.example.xclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto.response>> getUsers(){
            List<UserDto.response> userDtoList= userService.getUsers();
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }


    @GetMapping("/getBy/{id}")
    public ResponseEntity<UserDto.response> getUserById(@PathVariable Long id){
         UserDto.response userDto = userService.getById(id);

        return new ResponseEntity<>( userDto, HttpStatus.OK);
    }


    @PostMapping("/addUser")
    public ResponseEntity<UserDto.response>  createNewUser(@Valid @RequestBody UserDto.createRequest user){
        UserDto.response response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto.response> updateTheUser(@Valid @RequestBody UserDto.updateRequest user ,@PathVariable Long id ){
         UserDto.response userDto =  userService.updateUserById(user, id);

        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTheUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}
