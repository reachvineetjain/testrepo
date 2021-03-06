/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PaymentSchedule;

/**
 * @author ravi
 *
 */
@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Integer> {

}
