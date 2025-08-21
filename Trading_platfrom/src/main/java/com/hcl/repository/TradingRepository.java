package com.hcl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.TradingEntity;

@Repository
public interface TradingRepository extends JpaRepository<TradingEntity, Long> {
//   @Query("Select p FROM TradingEntity p Where p.email =:email")
//   Optional<TradingEntity> findByEmail(@Param("email") String email);
	
	Optional<TradingEntity> findByEmail(String email);
}
