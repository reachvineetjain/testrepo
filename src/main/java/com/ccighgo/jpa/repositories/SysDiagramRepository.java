/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Sysdiagram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SysDiagramRepository extends JpaRepository<Sysdiagram, Integer> {

}
