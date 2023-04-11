package com.example.nplus1.owner;

import com.example.nplus1.cat.Cat;
import com.example.nplus1.dog.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public List<String> findAllOwnerDogs() {
        List<Owner> owners = ownerRepository.findAll();

        return getDogNames(owners);
    }

    @Transactional(readOnly = true)
    public List<String> findAllJoinFetchDog() {
        List<Owner> owners = ownerRepository.findAllJoinFetchDog();

        return getDogNames(owners);
    }

    @Transactional(readOnly = true)
    public List<String> findAllEntityGraph() {
        List<Owner> owners = ownerRepository.findAllEntityGraph();

        return getDogNames(owners);
    }

    @Transactional(readOnly = true)
    public List<Owner> findAllOwnerWithBatch() {
        List<Owner> owners = ownerRepository.findAll();

        //Lazy Loading
        owners.stream()
                .map(Owner::getDogs)
                .flatMap(Collection::stream)
                .map(Dog::getName)
                .collect(Collectors.toList());

        owners.stream()
                .map(Owner::getCats)
                .flatMap(Collection::stream)
                .map(Cat::getName)
                .collect(Collectors.toList());
        return owners;
    }

    private List<String> getDogNames(List<Owner> owners) {
        return owners.stream()
                .map(Owner::getDogs)
                .flatMap(Collection::stream)
                .map(Dog::getName)
                .collect(Collectors.toList());
    }
}
