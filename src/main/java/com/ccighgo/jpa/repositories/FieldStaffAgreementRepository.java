/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffAgreement;

/**
 * @author ravi
 *
 */
@Repository
public interface FieldStaffAgreementRepository extends JpaRepository<FieldStaffAgreement, Integer> {

}
