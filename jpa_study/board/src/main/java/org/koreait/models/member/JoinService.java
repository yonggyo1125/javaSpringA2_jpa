package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.member.JoinForm;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinForm joinForm) {

    }

}
