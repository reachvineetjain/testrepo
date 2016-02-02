/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LookupCountry;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<LookupCountry, Integer> {

   LookupCountry findByCountryName(String CountryName);

   LookupCountry findByCountryCode(String countryCode);

   @Query("select c from LookupCountry c order by c.countryName ASC")
   List<LookupCountry> findAllCountryByNameOrderAsc();
}
