package orlanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orlanda.models.Item;
import orlanda.models.Person;
import orlanda.repositories.ItemsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;

    public List<Item> findByOwner(Person owner) {
        return itemsRepository.findByOwner(owner);
    }

    public List<Item> findByName(String name) {
        return itemsRepository.findByNameContaining(name);
    }

}
