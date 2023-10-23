package com.project.jpa1.service;

import com.project.jpa1.dto.UserDto;
import com.project.jpa1.entity.User;
import com.project.jpa1.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto login(UserDto dto){
        User user = new User();
        user = dto.toEntity();
        Optional<User> loginCheck = userRepository.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
        return loginCheck.map(User::toDto).orElse(null);
    }

    public List<UserDto> memberList(){
        List<User> list = userRepository.findAll();
        List<UserDto> dtoList = list.stream()
                .map(o -> new UserDto(o))
                .collect(toList());

        return dtoList;
    }

    public User join(UserDto userDto){
        User user = new User();
        user = userDto.toEntity();
        return userRepository.save(user);
    }

}
