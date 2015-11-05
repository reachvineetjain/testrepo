package com.ccighgo.service.components.storedprocedure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PartnerStoredProcedure {
	@PersistenceContext
	EntityManager em;

	public int PartnerSeasonAcceptedPax(int partnerId, int seasonId,
			int depPgmId, int depPgmOptionId) {
		Object result = em
				.createNativeQuery(
						"call SPPartnerSeasonAcceptedPax(:partnerId,:seasonId,:depPgmId,:depPgmOptionId)")
				.setParameter("partnerId", partnerId)
				.setParameter("seasonId", seasonId)
				.setParameter("depPgmId", depPgmId)
				.setParameter("depPgmOptionId", depPgmOptionId)
				.getSingleResult();

		if (result != null)
			return Integer.valueOf(String.valueOf(result));
		return 0;
	}

	public int PartnerSeasonPaxAllocated(int partnerId, int seasonId,
			int depPgmId) {
		Object result = em
				.createNativeQuery(
						"call PPartnerSeasonPaxAllocated(:partnerId,:seasonId,:depPgmId)")
				.setParameter("partnerId", partnerId)
				.setParameter("seasonId", seasonId)
				.setParameter("depPgmId", depPgmId).getSingleResult();

		if (result != null)
			return Integer.valueOf(String.valueOf(result));
		return 0;
	}

	public int PartnerSeasonCCIReviewPax(int partnerId, int seasonId,
			int depPgmId, int depPgmOptionId) {
		Object result = em
				.createNativeQuery(
						"call SPPartnerSeasonCCIReviewPax(:partnerId,:seasonId,:depPgmId,:depPgmOptionId)")
				.setParameter("partnerId", partnerId)
				.setParameter("seasonId", seasonId)
				.setParameter("depPgmId", depPgmId)
				.setParameter("depPgmOptionId", depPgmOptionId)
				.getSingleResult();

		if (result != null)
			return Integer.valueOf(String.valueOf(result));
		return 0;
	}
}
