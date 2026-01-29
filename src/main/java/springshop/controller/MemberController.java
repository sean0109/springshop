package springshop.controller;

import jakarta.validation.Valid;
import springshop.dto.MemberForm;
import springshop.exception.member.MemberSaveException;
import springshop.model.Address;
import springshop.model.Member;
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

       try {
            memberService.join(member);
        } catch (MemberSaveException e) {
            // 중복 회원 에러 메시지를 name 필드에 추가
            result.rejectValue("name", "duplicate", e.getMessage());
            result.reject("duplicateData", e.getMemberId());
            return "members/createMemberForm";
        } catch (Exception e) {
            // 기타 예외 처리
            result.reject("error", "회원가입 중 오류가 발생했습니다.");
            return "members/createMemberForm";
        }

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
