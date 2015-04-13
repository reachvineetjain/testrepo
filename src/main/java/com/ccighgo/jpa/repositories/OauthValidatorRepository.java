package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciOauthHistory;
import com.ccighgo.db.entities.CciOauthHistoryPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface OauthValidatorRepository extends JpaRepository<CciOauthHistory, CciOauthHistoryPK> {

}
