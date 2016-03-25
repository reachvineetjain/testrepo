/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerQuickStatsType;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerQuickStatsTypeRepository extends JpaRepository<PartnerQuickStatsType, Integer> {

}
