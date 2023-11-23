package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.ProductForAdminResponse
            (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name)
             from Products p
             where p.status <> 0
            """)
    List<ProductForAdminResponse> getAllProductByCreateDateDesc();

    @Query(value = """
            select new com.example.sd_95_polo_store_be.Model.
            Response.GetOneProductResponse
            (p.id, p.name, p.status, p.description, p.categories.id, p.brands.id,p.materials.id,p.brands.name, p.categories.name,p.materials.name)
             from Products p
             where p.id = :id and p.status <> 0
            """)
    Optional<GetOneProductResponse> getId(Integer id);
    @Query(value = """
            select Top 1 i.name from Products 
            join ProductDetail pd on Products.id = pd.productId
            join Images i on pd.id = i.productDetailId
            where productId = :id

              """, nativeQuery = true)
    String getImage(Integer id);

}
