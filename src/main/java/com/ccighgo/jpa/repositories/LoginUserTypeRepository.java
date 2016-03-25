package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LoginUserType;

@Repository
public interface LoginUserTypeRepository extends JpaRepository<LoginUserType, Integer> {

}
