package com.suchan.jpabook.service;

import com.suchan.jpabook.domain.item.Item;
import com.suchan.jpabook.respository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ItemService는 ItemRepository에 위임만 하고 있는 클래스인데, 이걸 사용할 필요성에 대해서는 고민해봐야 함.
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems(Item item) {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
