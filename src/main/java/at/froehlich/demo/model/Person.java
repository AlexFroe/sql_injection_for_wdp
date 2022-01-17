package at.froehlich.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
public class Person implements Serializable {

    @Id
    @Column(name = "per_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "per_firstname")
    private String firstName;

    @Column(name = "per_lastname")
    private String lastName;


    @Column(name = "per_sex")
    private String sex;


    public Person(String firstName, String lastName, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public Person(int id, String firstName, String lastName, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }
}
