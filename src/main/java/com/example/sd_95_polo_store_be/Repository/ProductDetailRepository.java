package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Response.DiscountProductDetailReponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Optional<ProductDetail> findById(Long id);

    @Query(value = """
                select new com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse(pd.id, pd.sizes.id, pd.colors.id, pd.discount.id, pd.discount.name,pd.discount.discount, pd.sizes.name,pd.colors.name,pd.quantity,pd.cost,pd.price,pd.status)
                from ProductDetail pd
                where pd.products.id = :productId
                and pd.status <> 0
            """)
    List<ProductDetailResponse> getByProductId(Long productId);

    @Query("""
                select new com.example.sd_95_polo_store_be.Model.Response.DiscountProductDetailReponse(p.id, p.products.name, p.price, d.discount) from ProductDetail p
                    join Discount d on d.id = p.discount.id
                where d.status = 1
            """)
    List<DiscountProductDetailReponse> getProductDiscounts();

}
