/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DocumentCategoryProcess;

/**
 * @author ravi
 *
 */
@Repository
public interface DocumentCategoryProcessRepository extends JpaRepository<DocumentCategoryProcess, Integer> {

}
