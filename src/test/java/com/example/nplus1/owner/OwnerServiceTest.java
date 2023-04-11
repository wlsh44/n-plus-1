package com.example.nplus1.owner;

import com.example.nplus1.cat.Cat;
import com.example.nplus1.dog.Dog;
import com.example.nplus1.dog.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OwnerServiceTest {

    @Autowired
    OwnerService ownerService;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    DogRepository dogRepository;

    @BeforeEach
    void init() {
        for (int i = 1; i <= 20; i += 2) {
            Owner owner = new Owner("주인" + (i / 2));
            owner.addDog(new Dog("강아지" + i));
            owner.addDog(new Dog("강아지" + (i + 1)));

            owner.addCat(new Cat("고양이" + i));
            owner.addCat(new Cat("고양이" + (i + 1)));
            ownerRepository.save(owner);
        }
    }

    @Test
    void findAll() throws Exception {
        List<String> dogNames = ownerService.findAllOwnerDogs();

        for (String dogName : dogNames) {
            System.out.println("dogName = " + dogName);
        }
    }

    @Test
    void findAllJoinFetchDog() throws Exception {
        List<String> dogNames = ownerService.findAllJoinFetchDog();
    }

    @Test
    void findAllEntityGraph() throws Exception {
        List<String> dogNames = ownerService.findAllEntityGraph();
    }

    @Test
    void findAllJoinFetchDogSize() throws Exception {
        List<Owner> owners = ownerRepository.findAllJoinFetchDog();

        System.out.println("집사 수 = " + owners.size());
        owners.forEach(System.out::println);
        assertThat(owners).hasSize(20);
    }

    @Test
    void findAllDistinctJoinFetchDogSize() throws Exception {
        List<Owner> owners = ownerRepository.findAllDistinctJoinFetchDog();

        System.out.println("집사 수 = " + owners.size());
        owners.forEach(System.out::println);
        assertThat(owners).hasSize(10);
    }

    @Test
    void findAllWithBatch() throws Exception {
        List<Owner> owners = ownerService.findAllOwnerWithBatch();

        System.out.println("집사 수 = " + owners.size());
        owners.forEach(System.out::println);
        assertThat(owners).hasSize(10);
    }

//    @Test
//    void findAllJoinFetchDogAndCat() throws Exception {
//        List<Owner> owners = ownerRepository.findAllJoinFetchDogAndCat();
//
//        System.out.println("집사 수 = " + owners.size());
//        owners.forEach(System.out::println);
//        assertThat(owners).hasSize(10);
//    }
//
//    @Test
//    void findAllEntityGraph2() throws Exception {
//        List<Owner> owners = ownerRepository.findAllEntityGraphDogAndCat();
//
//        System.out.println("집사 수 = " + owners.size());
//        owners.forEach(System.out::println);
//        assertThat(owners).hasSize(10);
//    }
}