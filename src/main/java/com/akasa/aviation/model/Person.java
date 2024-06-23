package com.akasa.aviation.model;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Data
@Entity
@Table(name="pilot")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy="native")
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private int age;
    @Column(name="email")
    private String email;


}
