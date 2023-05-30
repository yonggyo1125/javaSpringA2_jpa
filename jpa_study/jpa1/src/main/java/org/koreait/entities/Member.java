package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.constants.MemberType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users", indexes={
        @Index(name="idx_member_usernm", columnList = "userNm")
}) // 테이블 명이 user로 생성
public class Member {
    @Id
    //@TableGenerator(name = "user_seq")
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo; // 회원번호
    private String userId; // 회원 아이디
    private String userPw; // 회원 비밀번호
    private String userNm; // 회원명

    @Lob  // String - CLOB
    private String introduction; // 자기소개

    @Enumerated
    private MemberType memberType;

    @CreationTimestamp //  INSERT 쿼리시 자동으로 현재 날짜와 시간이 추가
    private LocalDateTime regDt; // 회원 가입일시

    @UpdateTimestamp // UPDATE 쿼리시 자동으로 현재 날짜와 시간이 수정
    private LocalDateTime modDt; // 회원 정보 수정일시
}
