package com.ccighgo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.OauthHistory;
import com.ccighgo.db.entities.OauthHistoryPK;
import com.ccighgo.jpa.repositories.OauthValidatorRepository;


public class ReplayValidator {
	private OauthValidatorRepository oaRepository;
	
	@Autowired
	public void setReplayValidatorDAO(OauthValidatorRepository dao){
		oaRepository = dao;
	}
	
	public void validate(HMACSignatureToken token) throws SecurityException{
		LOGGER.trace("enter validate");
		long timestamp = ValidateTimestamp(token.getTimeStamp());
		ValidateNonce(token.getNonce(), timestamp);
		LOGGER.trace("exit validate");
	}
	
	private long ValidateTimestamp(String timestamp) throws SecurityException{
		LOGGER.trace("enter ValidateTimestamp");
		long tsValue = Long.parseLong(timestamp);
		long curTimestamp = new Date().getTime()/1000;
		long offset = Math.abs(curTimestamp-tsValue);
		LOGGER.debug("Timestamp offset: {} ( {} - {} )", new Object[] {offset, curTimestamp, tsValue});
		if(offset > tolerance*1000)
			throw new SecurityException("The request timestamp exceeded tolerance level of " + tolerance + " seconds");

		LOGGER.trace("exit ValidateTimestamp");
		return tsValue;
	}
	
	private void ValidateNonce(String nonce, long timestamp) throws SecurityException{
		LOGGER.trace("enter ValidateNonce");
		try {
			LOGGER.debug("Retrieving OAUTH history entry for the timestamp and nonce");
			OauthHistoryPK pk = new OauthHistoryPK();
			pk.setNonce(nonce);
			pk.setTimevalue(timestamp);

			if(oaRepository.findOne(pk)!=null){
				LOGGER.debug("DONE: Found OAUTH history entry");
				throw new SecurityException("Potential replay: a request with same timestamp and nonce already served");
			}
			LOGGER.debug("DONE: Retrieving OAUTH history entry");
			OauthHistory oh = new OauthHistory();
			oh.setId(pk);
			oaRepository.save(oh);
		}
		catch(SecurityException ex){
			throw ex;
		}
		catch(Exception ex){
			LOGGER.error("Error in validation", ex);
			throw new SecurityException(ex);
		}
		LOGGER.trace("exit ValidateNonce");
	}
	
	public long getTolerance() {
		return tolerance;
	}

	public void setTolerance(long tolerance) {
		this.tolerance = tolerance;
		LOGGER.debug("Timestamp tolerance: {}", tolerance);
	}
	private long tolerance;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcurtOauthRealm.class); 
}
