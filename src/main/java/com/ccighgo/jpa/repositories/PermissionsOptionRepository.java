/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciPermissionsOption;

/**
 * @author sunny
 *
 */
@Repository
public interface PermissionsOptionRepository extends JpaRepository<CciPermissionsOption, Integer> {

}
