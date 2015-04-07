/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciFieldStaffAgreement;

/**
 * @author ravimishra
 *
 */
@Repository
public interface FieldStaffAgreementRepository extends JpaRepository<CciFieldStaffAgreement, Integer> {

}
