package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.constants.MemberType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users", indexes={
        @Index(name="idx_member_usernm", columnList = "userNm")
}) // 테이블 명이 user로 생성
public class Member extends BaseEntity {
    @Id
    //@TableGenerator(name = "user_seq")
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo; // 회원번호

    @Column(length=40, nullable=false, unique=true)
    private String userId; // 회원 아이디

    @Column(length=65, nullable=false, name="userPass") // 실제 테이블의 필드명 userPass
    private String userPw; // 회원 비밀번호

    @Column(length=40, nullable=false)
    private String userNm; // 회원명

    @Lob  // String - CLOB
   // @Transient // 엔티티 내부에서만 사용되는 항목 - 테이블 필드로 반영 X
    private String introduction; // 자기소개

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private MemberType memberType;

    @Temporal(TemporalType.DATE) // 날짜와 시간
    private Date birthDt;

    @OneToMany(mappedBy = "member", fetch=FetchType.LAZY)
    private List<BoardData> boardatas = new ArrayList<>();

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address address;
}
