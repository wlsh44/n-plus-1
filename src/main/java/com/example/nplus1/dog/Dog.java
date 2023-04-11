package com.example.nplus1.dog;

import com.example.nplus1.owner.Owner;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public void mappingOwner(Owner owner) {
        this.owner = owner;
    }
}

