package com.example.sd_95_polo_store_be.Model.Response;

import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Sizes,Integer> {
}
