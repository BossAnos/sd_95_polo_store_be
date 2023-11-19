package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Customers;
import com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.CustomerForAdminResponse(c.id,c.name,c.phone,c.name,c.avatar)
                from Customers c
              where c.status = "1"
            """)
    List<CustomerForAdminResponse> getCustomersForAdmin();
}
