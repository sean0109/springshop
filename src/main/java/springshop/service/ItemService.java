package springshop.service;

import springshop.model.Item;

import java.util.List;

public interface ItemService {

    // 상품 등록 및 수정
    void saveItem(Item item);
    // 상품 전체 조회
    List<Item> findAllItems();
    // 상품 하나 조회
    Item findItemById(Long id);
    // 상품 명으로 조회
    List<Item> findItemByName(String name);
}
