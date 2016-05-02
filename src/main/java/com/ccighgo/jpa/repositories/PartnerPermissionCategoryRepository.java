/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Airport;
import com.ccighgo.db.entities.PartnerPermissionsCategory;

/**
 * @author Ahmed
 *
 */
@Repository
public interface PartnerPermissionCategoryRepository extends JpaRepository<PartnerPermissionsCategory, Integer> {

}
