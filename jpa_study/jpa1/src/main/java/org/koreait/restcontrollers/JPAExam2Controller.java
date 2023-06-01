package org.koreait.restcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.constants.MemberType;
import org.koreait.entities.Address;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.models.board.BoardListService;
import org.koreait.repositories.AddressRepository;
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
    private final BoardListService listService;
    private final AddressRepository addressRepository;

    private void insertData() {

        Address address = Address.builder()
                .zipcode("10000")
                .address("주소!")
                .addressSub("나머지 주소!!!")
                .build();

        address = addressRepository.saveAndFlush(address);

        Member member = Member.builder()
                .userId("user01")
                .userNm("사용자01")
                .userPw("123456")
                .memberType(MemberType.USER)
                .address(address)
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
       String userId = member.getUserId();// Member쪽 sql이 실행
       log.info(userId);
        //log.info(member.toString());
    }

    @GetMapping("/ex03")
    public void ex03() {
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> items = member.getBoardatas();
        items.stream().forEach(System.out::println);
    }

    @GetMapping("/ex04")
    public void ex04() {
        List<BoardData> items = boardDataRepository.findAll();
        for (BoardData item : items) {
            Member member = item.getMember();
            String userId = member.getUserId();
        }

    }

    @GetMapping("/ex05")
    public void ex05() {
        List<BoardData> items = boardDataRepository.getBoardDatas();
    }

    @GetMapping("/ex06")
    public void ex06() {
        List<BoardData> items = listService.gets();
    }
}
