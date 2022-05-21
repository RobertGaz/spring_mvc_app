package orlanda.dao.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import orlanda.models.Person;

import java.util.List;

@Component
public class HibernateDao implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person").list();
    }

    @Override
    @Transactional
    public Person getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Override
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Override
    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person personInDb = session.get(Person.class, id);
        personInDb.copy(person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }
}
