package springshop.mapper;

import org.apache.ibatis.annotations.Mapper;
import springshop.model.Item;

import java.util.List;

@Mapper
public interface ItemMapper {
    void save(Item item);   // 저장
    Item findById(Integer id);
    List<Item> findAll();
    Item findByName(String name);

}
