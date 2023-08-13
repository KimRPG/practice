package com.hunsung.board.entity;


import com.hunsung.board.dto.SignUpDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "User") //-해당클래스를 Entity클래스로 사용하겠다.
@Table(name = "User") //-데이터베이스에 있는 해당하는 테이블과 현재 클래스를 매핑시킴
@Data
public class UserEntity {
    @Id//USerEmail을 PrimaryKey로 사용한다.
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userPhoneNumber;
    private String userAddress;
    private String userProfile;


    public UserEntity(SignUpDto dto){
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNickname = dto.getUserNickname ();
        this.userPhoneNumber = dto.getUserPhoneNumber();
        this.userAddress = dto.getUserAddress() + dto.getUserAddressDetail();
    }
}

