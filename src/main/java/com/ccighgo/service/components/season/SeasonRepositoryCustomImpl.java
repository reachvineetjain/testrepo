package com.ccighgo.service.components.season;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ccighgo.jpa.repositories.SeasonsRepositoryCustom;


@Component
public class SeasonRepositoryCustomImpl implements SeasonsRepositoryCustom{

	@Autowired
	EntityManager entityManager;
	@Override
	public String cloneSeason(String seasonId, String newSeasonName) {
		String query = "call SPSeasonClone(?,?)";
		Connection cc = ((SessionImpl) entityManager.getDelegate()).connection();
		
		 Query nq = entityManager.createNativeQuery(query);
		 nq.setParameter(1, Integer.parseInt(seasonId));
		 nq.setParameter(2, newSeasonName);
		 
		 List<?> result =  entityManager.createNativeQuery(query).getResultList();
		 return "";
	}
	
  
	
	public List findSeasonByName(String name) {
		List seasonsList = entityManager.createQuery(
		    "SELECT c FROM Season c WHERE c.seasonName LIKE :custName").setParameter("custName", name).getResultList();
		return seasonsList;
	}
//	public List findSeasonByDepartment(String departmentId){
//		List seasonsList = entityManager.createQuery(
//			    "SELECT c FROM Season c WHERE c.departmentId LIKE :departmentId").setParameter("departmentId", departmentId).getResultList();
//		return seasonsList;
//	}
	
}