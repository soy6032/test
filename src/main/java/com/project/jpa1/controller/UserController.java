package com.project.jpa1.controller;

import com.project.jpa1.dto.NoteDto;
import com.project.jpa1.dto.UserDto;
import com.project.jpa1.entity.Note;
import com.project.jpa1.entity.User;
import com.project.jpa1.repository.UserRepository;
import com.project.jpa1.service.NoteService;
import com.project.jpa1.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NoteService noteService;

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto dto, HttpSession session){
        dto = userService.login(dto);
        if(dto != null) {
            session.setAttribute("user", dto);
            session.setMaxInactiveInterval(12000);
            return dto;
        }
        else {
            return null;
        }
    }

    @PostMapping("/join")
    public User join(@RequestBody UserDto dto, HttpSession session){
        User user = userService.join(dto);
        return user;
    }

    @GetMapping("/list")
    public List<NoteDto> memberList(){
        return noteService.list();
    }

    @GetMapping("/list/{noteIdx}")
    public List<NoteDto> detail(@PathVariable Long noteIdx){
        return noteService.Detail(noteIdx);
    }

}
