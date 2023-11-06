package com.example.sd_95_polo_store_be.Model.Response;

import com.example.sd_95_polo_store_be.Model.Entity.Meterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterialRepository extends JpaRepository<Meterials,Integer> {
}
