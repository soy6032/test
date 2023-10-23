package com.project.jpa1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.jpa1.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    private String userId;

    private String userPw;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String joinDate;

    public UserDto toDto(){
        UserDto userDto = new UserDto();
        userDto.setUserId(this.userId);
        userDto.setUserPw(this.userPw);
        return userDto;
    }
}
