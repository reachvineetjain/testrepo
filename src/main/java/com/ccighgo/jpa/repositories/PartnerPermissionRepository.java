package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;

@Repository
public interface PartnerPermissionRepository extends JpaRepository<PartnerPermission, Integer> {
   
  /* @Query("SELECT l FROM PartnerPermission l where l.partnerUser. = ?1")
   public PartnerPermission findByPartnerUserId(PartnerUser partnerUser);*/

   @Query( value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?1", nativeQuery = true)
   public List<String> getTableMetaData(String tableName);
   
   

}