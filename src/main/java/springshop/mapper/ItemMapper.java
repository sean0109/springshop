package springshop.mapper;

import org.apache.ibatis.annotations.Mapper;
import springshop.model.Item;

import java.util.List;

@Mapper
public interface ItemMapper {
    void updateItem(Item item);
    void insertItem(Item item);
    Item findById(Long id);
    List<Item> findAll();
    Item findByName(String name);
    List<Item> findItemByName(String name);

}
