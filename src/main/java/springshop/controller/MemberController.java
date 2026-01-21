package springshop.controller;

import jakarta.validation.Valid;
import springshop.domain.Address;
import springshop.domain.Member;
import springshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {

        // form 객체 화면과 매핑
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            // 폼 다시 보여주면서 에러 출력
            return "members/createMemberForm";
        }

        Address address = Address.builder()
                .city(form.getCity())
                .street(form.getStreet())
                .zipcode(form.getZipcode())
                .build();

        Member member = Member.builder()
                .name(form.getName())
                .address(address)
                .build();

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String getList(Model model) {

        // 리스트로 받아오기
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        return "members/memberList";
    }
}
