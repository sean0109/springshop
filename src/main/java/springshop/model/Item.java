package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // Mybatis 매핑용
public class Item {
    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;
    private String dtype;  // BOOK, MOVIE, ALBUM

    // Book 전용 (nullable)
    private String author;
    private String isbn;

    // Movie 전용 (nullable)
    private String director;
    private String actor;

    // Album 전용 (nullable)
    private String artist;
    private String etc;

    @Builder
    public Item(Long itemId, String name, int price, int stockQuantity,
                String dtype, String author, String isbn, String director,
                String actor, String artist, String etc) {

        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.dtype = dtype;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
        this.artist = artist;
        this.etc = etc;
    }
}