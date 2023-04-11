package com.example.nplus1.owner;

import com.example.nplus1.cat.Cat;
import com.example.nplus1.dog.Dog;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private final List<Dog> dogs = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private final List<Cat> cats = new ArrayList<>();

    public Owner(String name) {
        this.name = name;
    }

    public void addDog(Dog dog) {
        this.dogs.add(dog);
        dog.mappingOwner(this);
    }

    public void addCat(Cat cat) {
        this.cats.add(cat);
        cat.mappingOwner(this);
    }
}
