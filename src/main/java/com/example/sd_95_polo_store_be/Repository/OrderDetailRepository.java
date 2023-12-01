package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.OderDetail;
import com.example.sd_95_polo_store_be.Model.Response.OrderDetailResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OderDetail,Integer> {
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.OrderDetailResponse
            (od.id ,od.productDetail.id,od.productDetail.products.name,od.productDetail.sizes.name,od.productDetail.colors.name,od.price,od.quantity)
             from OderDetail od
             where od.oders.id = :id
            """)
    List<OrderDetailResponse> getId(Integer id);

    @Query(value = """
            select Top 1 i.name from OderDetail 
            join ProductDetail pd on OderDetail.productDetailId = pd.id
            join Images i on pd.id = i.productDetailId
            where OderDetail.id = :id 
              """, nativeQuery = true)
    String getImage(@Param("id") Integer id);
}
