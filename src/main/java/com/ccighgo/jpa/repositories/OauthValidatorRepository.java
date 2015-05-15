package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.OauthHistory;
import com.ccighgo.db.entities.OauthHistoryPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface OauthValidatorRepository extends JpaRepository<OauthHistory, OauthHistoryPK> {

}
