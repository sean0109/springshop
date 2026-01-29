package springshop.service.impl;

import springshop.exception.member.MemberSaveException;
import springshop.model.Member;
import springshop.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springshop.service.MemberService;

import java.util.List;

// Mybatis를 이용한 비지니스 로직
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    // 회원가입
    @Override
    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);
        memberMapper.save(member);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberMapper.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new MemberSaveException(member.getName(), "이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    @Override
    public List<Member> findMembers() {
        return memberMapper.findAll();
    }

    // 회원 단건 조회
    @Override
    public Member findOne(Long memberId) {
        return memberMapper.findById(memberId);
    }
}
