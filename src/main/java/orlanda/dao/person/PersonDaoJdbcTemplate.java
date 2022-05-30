package orlanda.dao.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import orlanda.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDaoJdbcTemplate implements PersonDao {

    class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            return person;
        }
    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public Person getById(int id) {
        // если названия столбцов и полей класса одинаковые, можем не создавать
        // свой PersonMapper, а использовать BeanPropertyRowMapper
        return jdbcTemplate.query("select * from person where id = ? ", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age,email) values (?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());

    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("update person set name = ?, age = ?, email=? where id = ?",
                person.getName(), person.getAge(), person.getEmail(), person.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }

    public long multipleUpdate() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person(0, "Usual update " + i, 30, "abc@def.gh");
            people.add(person);
        }

        long start = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("insert into person(name, age,email) values (?, ?, ?)",
                    person.getName(), person.getAge(), person.getEmail());
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    public long batchUpdate() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person(0, "Batch update " + i, 30, "abc@def.gh");
            people.add(person);
        }

        long start = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("insert into person(name, age,email) values (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, people.get(i).getName());
                        ps.setInt(2, people.get(i).getAge());
                        ps.setString(3, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });


        long end = System.currentTimeMillis();

        return end - start;
    }
}
