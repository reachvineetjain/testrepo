/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.service.transport.fieldstaff.beans.season.details.PaymentSchedule;

/**
 * @author ravi
 *
 */
@Repository
public interface PaymentScheuleRepository extends JpaRepository<PaymentSchedule, Integer> {

}
