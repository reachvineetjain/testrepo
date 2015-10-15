/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerQuickStatsType;
import com.ccighgo.db.entities.PartnerWorkQueueType;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerQuickStatsTypeRepository extends JpaRepository<PartnerQuickStatsType, Integer> {

}
