package springshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {

    private Long id;    // 키값

    @NotEmpty(message = "상품 명은 필수 입니다.")
    private String name;
    private int price;
    private int stockQuantity;
    private String dtype;
    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String actor;


}
