package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.MemberType;

import java.util.ArrayList;
import java.util.List;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long userNo; // 회원번호

    @Column(length=40, nullable = false, unique = true)
    private String userId; // 아이디

    @Column(length=65, nullable = false)
    private String userPw; // 비밀번호

    @Column(length=40, nullable=false)
    private String userNm; // 회원명

    @Column(length=100)
    private String email; // 이메일

    @Column(length=11)
    private String mobile; // 휴대전화 번호

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private MemberType type = MemberType.USER; // USER - 사용자, ADMIN - 관리자

    @Column(length=10)
    private String zipcode;
    @Column(length=100)
    private String address;
    @Column(length=100)
    private String addressSub;

    @OneToMany(mappedBy = "member")
    private List<BoardData> boardDatas = new ArrayList<>();
}
