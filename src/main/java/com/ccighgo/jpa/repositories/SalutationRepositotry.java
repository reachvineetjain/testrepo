package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Salutation;

@Repository
public interface SalutationRepositotry extends JpaRepository<Salutation, Integer> {

}
