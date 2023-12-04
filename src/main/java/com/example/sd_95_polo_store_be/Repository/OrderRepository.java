package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Oders,Integer> {
    List<Oders> findByCustomersId(Integer id);
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.OrderResponse
            (o.id, o.username,o.phone,o.address,o.totalPrice,o.transactions.name,o.note,o.status,o.confirmDate,o.successDate,o.shipDate,o.createDate)
             from Oders o
             where o.id = :id
            """)
    Optional<OrderResponse> getId(Integer id);
}
