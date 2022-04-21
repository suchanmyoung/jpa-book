package com.suchan.jpabook.respository;

import com.suchan.jpabook.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) { // JPA에 저장할 때 까지 아이디가 없음.그럼 persist 로 신규로 등록
            em.persist(item);
        } else{
            em.merge(item); // update랑 비슷함. 자세한 건 뒤에
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item  i", Item.class).getResultList();
    }



}
