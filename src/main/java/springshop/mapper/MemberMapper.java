package springshop.mapper;

import springshop.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    List<Member> findByName(String name);
}
