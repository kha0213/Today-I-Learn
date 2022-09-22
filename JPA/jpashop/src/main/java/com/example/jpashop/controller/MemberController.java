package com.example.jpashop.controller;

import com.example.jpashop.domain.Member;
import com.example.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }

    @GetMapping("/members/new")
    public String joinView() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String join(@Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        memberService.join(member);
        return "redirect:/members";
    }
}
