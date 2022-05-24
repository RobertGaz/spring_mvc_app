package orlanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orlanda.models.Person;
import orlanda.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    public Person getById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public void test() {
        System.out.println("DEBUG...");
    }
}
