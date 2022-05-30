package orlanda.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table
public class Person {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message="Name shouldn't be empty")
    @Size(min=2, max=30, message="Incorrect name size")
    @Column(name="name")
    private String name;

    @Min(value=0, message="Invalid age")
    @Column(name="age")
    private int age;

    @NotEmpty(message="E-mail shouldn't be empty")
    @Email(message="Invalid e-mail")
    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    public Person() {

    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void copy(Person other) {
        this.name = other.name;
        this.age = other.age;
        this.email = other.email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
