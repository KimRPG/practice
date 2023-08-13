package com.hunsung.board.controller;

import com.hunsung.board.dto.PatchUserDto;
import com.hunsung.board.dto.PatchUserResponseDto;
import com.hunsung.board.dto.ResponseDto;
import com.hunsung.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired UserService userService;
    @PatchMapping("/")
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserDto requestBody, @AuthenticationPrincipal String userEmail){
        return userService.patchUser(requestBody, userEmail);
    }
}
