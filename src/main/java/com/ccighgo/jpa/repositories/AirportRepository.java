/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Airport;

/**
 * @author Ahmed
 *
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

   @Query("select d from Airport d where d.airportName = ?1 ")
   public List<Airport> getAirportByName(String airportName);

   @Query("select d from Airport d where d.isInternational=0")
   public List<Airport> getAirportInUSA();

}
