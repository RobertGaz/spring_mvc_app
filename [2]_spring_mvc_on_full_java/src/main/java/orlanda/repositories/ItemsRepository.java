package orlanda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orlanda.models.Item;
import orlanda.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    // owner.getItems();
    List<Item> findByOwner(Person owner);

//    where name like "%string%"
    List<Item> findByNameContaining(String s);
}
