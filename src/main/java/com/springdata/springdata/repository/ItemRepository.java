package com.springdata.springdata.repository;

import com.springdata.springdata.entity.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT it FROM Item it " +
            "WHERE it.modelName ilike concat('%', :str, '%') " +
            "OR it.description ilike concat('%', :str, '%')")
    List<Item> getItemsFromWord(String str);

    Item findItemByCostAndAndDescription(int cost, String description);

}
