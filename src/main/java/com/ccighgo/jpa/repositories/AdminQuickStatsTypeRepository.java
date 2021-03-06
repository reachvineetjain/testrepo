/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsType;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsTypeRepository extends JpaRepository<AdminQuickStatsType, Integer> {

}
