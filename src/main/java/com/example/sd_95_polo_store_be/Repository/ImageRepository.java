package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Images, Integer> {
//    @Query(value = """
//                select new com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse(img.id,img.name,img.url_image,img.status)
//                from Images img
//                where img.products.id = :id and img.status <> 0
//            """)
//    List<ImageProductResponse> findImagesByIdProduct(Long id);
}
