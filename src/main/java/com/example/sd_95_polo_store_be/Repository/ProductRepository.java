package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    @Query(value = """
                    select new com.example.sd_95_polo_store_be.Model.
                    Response.ProductForAdminResponse
                    (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name)
                     from Products p
                     where p.status <> 0
                    """)
    List<ProductForAdminResponse> getAllProductByCreateDateDesc();

    @Query(value = """
            select i.source from Products 
            join ProductDetail pd on product.id = pd.products.id
            join Images i on pd.id = i.productDetail.id
            where product_id = :id
            limit 1
        """,nativeQuery = true)
    String getImage(Long id);

}
