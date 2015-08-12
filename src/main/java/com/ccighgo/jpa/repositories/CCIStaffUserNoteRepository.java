package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUserNote;

@Repository
public interface CCIStaffUserNoteRepository extends JpaRepository<CCIStaffUserNote, Integer> {

}

