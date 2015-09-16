/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonContract;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonContractRepository extends JpaRepository<PartnerSeasonContract, Integer> {

}
