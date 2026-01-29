package springshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemForm {

    private Long itemId;    // 키값

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

    @Builder
    public ItemForm(Long itemId, String name, int price, int stockQuantity, String dtype, String artist, String etc, String author, String isbn, String director, String actor) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.dtype = dtype;
        this.artist = artist;
        this.etc = etc;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
    }
}
