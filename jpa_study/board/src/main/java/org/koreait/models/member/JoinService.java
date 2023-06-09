package org.koreait.models.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.constants.MemberType;
import org.koreait.controllers.member.JoinForm;
import org.koreait.entities.Member;
import org.koreait.models.member.social.ProfileResult;
import org.koreait.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void join(JoinForm joinForm) {
        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setType(MemberType.USER);

        ProfileResult profileResult = (ProfileResult) session.getAttribute("kakao");
        if (profileResult == null) {
            String hash = passwordEncoder.encode(joinForm.getUserPw());
            member.setUserPw(hash);
        } else {
            String socialId = "" + profileResult.getId();
            String socialChannel = "kakao";
            member.setSocialId(socialId);
            member.setSocialChannel(socialChannel);
        }

        repository.saveAndFlush(member);
    }
}
