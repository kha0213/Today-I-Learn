package com.example.springbasic.api;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
}
