package com.example.nplus1.owner;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select o from Owner o join fetch o.dogs")
    List<Owner> findAllJoinFetchDog();

    @EntityGraph(attributePaths = {"dogs"})
    @Query("select o from Owner o")
    List<Owner> findAllEntityGraph();

    @Query("select distinct o from Owner o join fetch o.dogs")
    List<Owner> findAllDistinctJoinFetchDog();
//
//    @Query("select o from Owner o join fetch o.dogs join fetch o.cats")
//    List<Owner> findAllJoinFetchDogAndCat();

//    @EntityGraph(attributePaths = {"dogs", "cats"})
//    @Query("select o from Owner o")
//    List<Owner> findAllEntityGraphDogAndCat();

//    @Query("select o from Owner o ")
//    List<Owner> findAllWithBatch();
}
