package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffWorkQueueCategory;

@Repository
public interface FieldStaffWorkQueueCategoriesRepository extends JpaRepository<FieldStaffWorkQueueCategory, Integer> {

   @Query("SELECT f FROM FieldStaffWorkQueueCategory f WHERE f.fieldStaffWorkQueueType.fieldStaffWQTypeId = ?1")
   public List<FieldStaffWorkQueueCategory> findAllCategoriesByTypeId(Integer typeId);
}
