package org.koreait.restcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.constants.MemberType;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log
@RestController
@RequestMapping("/jpa/exam2")
@RequiredArgsConstructor
public class JPAExam2Controller {
    private final MemberRepository memberRepository;
    private final BoardDataRepository boardDataRepository;

    private void insertData() {
        Member member = Member.builder()
                .userId("user01")
                .userNm("사용자01")
                .userPw("123456")
                .memberType(MemberType.USER)
                .build();

        member = memberRepository.saveAndFlush(member);

        List<BoardData> items = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            BoardData item = BoardData.builder()
                    .poster("작성자" + i)
                    .subject("제목" + i)
                    .content("내용" + i)
                    .member(member)
                    .build();
            items.add(item);
        }

        boardDataRepository.saveAllAndFlush(items);
    }

    @GetMapping("/ex01")
    public void ex01() {
        insertData();
    }

    @GetMapping("/ex02")
    public void ex02() {
        BoardData boardData = boardDataRepository.findById(1L).orElse(null);
        //log.info(boardData.toString());

        Member member = boardData.getMember();
        String userId = member.getUserId();
        log.info(userId);
        //log.info(member.toString());
    }

    @GetMapping("/ex03")
    public void ex03() {
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> items = member.getBoardatas();
        items.stream().forEach(System.out::println);
    }
}
