/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAnnouncement;
import com.ccighgo.db.entities.HostFamilyMember;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilyMemberRepository extends JpaRepository<HostFamilyMember, Integer> {

	HostFamilyMember getHFMember(int goId);
   
}
