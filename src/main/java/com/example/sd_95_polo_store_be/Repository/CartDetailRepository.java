package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
