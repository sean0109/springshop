package springshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springshop.mapper.ItemMapper;
import springshop.model.Item;
import springshop.service.ItemService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    // 상품 저장
    @Override
    @Transactional
    public void saveItem(Item item) {

        Long itemId = item.getItemId();

        if (itemId != null) {
            itemMapper.updateItem(item);
        }else {
            itemMapper.insertItem(item);
        }

    }

    @Override
    public List<Item> findAllItems() {

        return itemMapper.findAll();
    }

    @Override
    public Item findItemById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findItemByName(String name) {
        return itemMapper.findItemByName(name);
    }
}
