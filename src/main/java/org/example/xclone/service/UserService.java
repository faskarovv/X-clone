package org.example.xclone.service;


import org.example.xclone.exception.EntityAlreadyInUse;
import org.example.xclone.exception.EntityNotFoundException;
import org.example.xclone.model.dto.UserDto;
import org.example.xclone.model.entity.User;
import org.example.xclone.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
      UserRepo userRepo;

     @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<UserDto.response> getUsers() {
            List<User> responseList = userRepo.findAll();

           return  responseList.stream()
                    .map(user->mapToUserDto(user))
                    .collect(Collectors.toList());
     }

         private UserDto.response mapToUserDto(User user) {
            return new UserDto.response(
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole()
            );
        }


    public UserDto.response getById(Long id) {
           User user = userRepo.findById(id).orElse(null);

           UserDto.response userDto = new UserDto.response();
           userDto.setEmail(user.getEmail());
           userDto.setUsername(user.getUsername());
           userDto.setId(user.getUserId());

           return userDto;
    }


    public UserDto.response createUser(UserDto.createRequest userDto) {
         User existingUser = userRepo.findByEmail(userDto.getEmail());

         if(existingUser != null){
             throw new EntityAlreadyInUse("there exist a userDto with same email");
         }

         User newUser = new User();
         newUser.setUsername(userDto.getUsername());
         newUser.setEmail(userDto.getEmail());
         newUser.setPassword(userDto.getPassword());
         newUser.setRole(userDto.getRole());

         User savedUser = userRepo.save(newUser);

         UserDto.response response = new UserDto.response();
         response.setId(savedUser.getUserId());
         response.setUsername(savedUser.getUsername());
         response.setEmail(savedUser.getEmail());

         return response;
    }


    public UserDto.response updateUserById(UserDto.updateRequest user, Long id) {
        User exisitingCustomer = userRepo.findById(id).orElse(null);

        UserDto.response response;
        if (exisitingCustomer == null) {
            throw new EntityNotFoundException("such user does not exist");
        } else {
            User updatedUser = new User();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setRole(user.getRole());

            User savedUser = userRepo.save(updatedUser);

            response = new UserDto.response();
            response.setId(savedUser.getUserId());
            response.setUsername(savedUser.getUsername());
            response.setEmail(savedUser.getEmail());
        }

        return response;
    }

    public void deleteUser(Long id) {
         User user = userRepo.findById(id).orElse(null);

         if(user == null){
             throw new EntityNotFoundException("No such user exist with same ID");
         }
         else{
             userRepo.delete(user);
         }
    }
}
