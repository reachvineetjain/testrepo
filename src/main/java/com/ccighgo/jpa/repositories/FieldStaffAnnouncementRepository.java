package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffAnnouncement;
import com.ccighgo.utils.CCIConstants;

@Repository
public interface FieldStaffAnnouncementRepository extends JpaRepository<FieldStaffAnnouncement, Integer> {
   
   @Query("SELECT a FROM FieldStaffAnnouncement a WHERE a.showERD = " + CCIConstants.ACTIVE)
   List<FieldStaffAnnouncement> getERDStaffAnnouncements();

}
