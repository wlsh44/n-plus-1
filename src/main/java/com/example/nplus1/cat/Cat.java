package com.example.nplus1.cat;

import com.example.nplus1.owner.Owner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public void mappingOwner(Owner owner) {
        this.owner = owner;
    }
}

