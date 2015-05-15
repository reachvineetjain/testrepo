package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.OauthHistory;

/**
 * @author ravimishra
 *
 */
@Repository
public interface OauthHistoryRepository extends JpaRepository<OauthHistory, Integer> {

}
