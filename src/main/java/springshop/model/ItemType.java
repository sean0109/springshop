package springshop.model;

public enum ItemType {
    BOOK, MOVIE, ALBUM;

    // private 생성자 default

    // 타입 검증 및 반환 ( 정적 팩토리 메서드 )
    public static ItemType from (String dtype){
        try {
            return ItemType.valueOf(dtype.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 상품 타입 입니다.");
        }
    }
}
