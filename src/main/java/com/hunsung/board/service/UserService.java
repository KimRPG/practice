package com.hunsung.board.service;

import com.hunsung.board.dto.PatchUserDto;
import com.hunsung.board.dto.PatchUserResponseDto;
import com.hunsung.board.dto.ResponseDto;
import com.hunsung.board.entity.UserEntity;
import com.hunsung.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class UserService {
    @Autowired UserRepository userRepository;

    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserDto dto , String userEmail) {

        UserEntity userEntity = null;
        String userNickname = dto.getUserNickname();
        String userProfile = dto.getUserProfile();
        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) {
                return ResponseDto.setFailed("Dose Not Exist User");
            }
            userEntity.setUserNickname(userNickname);
            userEntity.setUserProfile(userProfile);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
        userEntity.setUserPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        return ResponseDto.setSuccess("Success",patchUserResponseDto );
    }

}
