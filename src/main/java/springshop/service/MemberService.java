package springshop.service;

import springshop.domain.Member;

import java.util.List;

public interface MemberService {

    // 회원 가입
    void join(Member member);
    // 회원 전체 조회
    List<Member> findMembers();
    // 회원 단건 조회
    Member findOne(Long memberId);

}
