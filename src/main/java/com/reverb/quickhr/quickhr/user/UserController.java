package com.reverb.quickhr.quickhr.user;

import com.reverb.quickhr.quickhr.commons.exceptions.ErrorResponseDto;
import com.reverb.quickhr.quickhr.user.dtos.CreateUserRequestDto;
import com.reverb.quickhr.quickhr.user.dtos.LoginUserRequestDto;
import com.reverb.quickhr.quickhr.user.dtos.UserResponseDto;
import com.reverb.quickhr.quickhr.user.exceptions.InvalidPasswordException;
import com.reverb.quickhr.quickhr.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(createUserRequestDto);
        return ResponseEntity.created(URI.create("/user/"+userResponseDto.getId())).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        UserResponseDto userResponseDto = userService.verifyUser(loginUserRequestDto);
        return ResponseEntity.accepted().body(userResponseDto);
    }

    @ExceptionHandler({
            UserNotFoundException.class,
            InvalidPasswordException.class
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception e) {
        if(e instanceof UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getMessage()));
    }
}
