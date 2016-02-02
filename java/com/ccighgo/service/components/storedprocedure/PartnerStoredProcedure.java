package com.ccighgo.service.components.storedprocedure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
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
						"call SPPartnerSeasonPaxAllocated(:partnerId,:seasonId,:depPgmId)")
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
	public int PartnerSeasonPaxOpenings (int partnerId, int seasonId,
         int depPgmId, int depPgmOptionId) {
      Object result = em
            .createNativeQuery(
                  "call SPPartnerSeasonPaxOpenings(:partnerId,:seasonId,:depPgmId,:depPgmOptionId)")
            .setParameter("partnerId", partnerId)
            .setParameter("seasonId", seasonId)
            .setParameter("depPgmId", depPgmId)
            .setParameter("depPgmOptionId", depPgmOptionId).getFirstResult();
           

      if (result != null)
         return Integer.valueOf(String.valueOf(result));
      return 0;
   }
}
