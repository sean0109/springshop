package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor  // Mybatis 리플렉션으로 값 주입. Setter 없어도 됨
public class Member {

    private Long id;
    private String name;
    private Address address;

    @Builder
    public Member(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
