package com.reverb.quickhr.quickhr.user;

import com.reverb.quickhr.quickhr.security.jwt.JwtService;
import com.reverb.quickhr.quickhr.user.dtos.CreateUserRequestDto;
import com.reverb.quickhr.quickhr.user.dtos.LoginUserRequestDto;
import com.reverb.quickhr.quickhr.user.dtos.UserResponseDto;
import com.reverb.quickhr.quickhr.user.exceptions.InvalidPasswordException;
import com.reverb.quickhr.quickhr.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = modelMapper.map(createUserRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserResponseDto userResponseDto = modelMapper.map(savedUserEntity, UserResponseDto.class);
        String token = jwtService.createJwt(savedUserEntity.getUsername());
        userResponseDto.setToken(token);
        return userResponseDto;
    }

    public UserResponseDto verifyUser(LoginUserRequestDto loginUserRequestDto) {
        UserEntity userEntity = userRepository.findByEmail(loginUserRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException());
        if(userEntity == null) {
            throw new UserNotFoundException();
        }
        if(!passwordEncoder.matches(loginUserRequestDto.getPassword(), userEntity.getPassword())) {
            throw new InvalidPasswordException();
        }
        UserResponseDto userResponseDto = modelMapper.map(userEntity, UserResponseDto.class);
        String token = jwtService.createJwt(userEntity.getUsername());
        userResponseDto.setToken(token);
        return userResponseDto;
    }

    public UserResponseDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new); // == () -> new UserNotFoundException()
        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    public UserResponseDto getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
        return modelMapper.map(userEntity, UserResponseDto.class);
    }
}
