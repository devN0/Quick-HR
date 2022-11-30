package com.reverb.quickhr.quickhr.about;

import com.reverb.quickhr.quickhr.commons.exceptions.ErrorResponseDto;
import com.reverb.quickhr.quickhr.user.dtos.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController {
    @GetMapping("/private")
    public String privateAbout(@AuthenticationPrincipal UserResponseDto userResponseDto) {
        return "It's a private page and you are viewing it as " + userResponseDto.getUsername();
    }
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getMessage()));
    }
}
