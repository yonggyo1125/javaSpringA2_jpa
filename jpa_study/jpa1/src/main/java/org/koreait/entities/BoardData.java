package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity @Data
public class BoardData extends BaseEntity {
    @Id @GeneratedValue
    private Long id; // 게시글 번호

    @Column(nullable=false)
    private String subject;

    @Lob
    @Column(nullable=false)
    private String content;

    @Column(length=40, nullable=false)
    private String poster;
}
