package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Address extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(length=10, nullable = false)
    private String zipcode; // 우편번호

    @Column(length=100, nullable = false)
    private String address; // 주소

    @Column(length=100)
    private String addressSub; // 나머지 주소
}
