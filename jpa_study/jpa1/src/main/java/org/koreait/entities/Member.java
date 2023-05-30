package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    private Long userNo; // 회원번호
    private String userId; // 회원 아이디
    private String userPw; // 회원 비밀번호
    private String userNm; // 회원명
}
