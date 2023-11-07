package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Service.ProductService;
import com.example.sd_95_polo_store_be.common.Mapper.EntityMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private EntityMapper entityMapper;
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.entityMapper = new EntityMapper();
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductRequset productRequset) {
        if (productRequset.getName() == null || productRequset.getName().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }

        if (productRequset.getStatus() == null) {
            throw new IllegalArgumentException("Trạng thái sản phẩm không được để trống");
        }

        // Kiểm tra các trường dữ liệu khác và thực hiện các kiểm tra bổ sung tùy theo yêu cầu của bạn

        // Tạo một đối tượng Product từ ProductRequest
        Products product = new Products();
        product.setName(productRequset.getName());
        product.setStatus(productRequset.getStatus());
        product.setDescription(product.getDescription());
        product.setCategoryId(product.getCategoryId());
        product.setBrandId(productRequset.getBrandId());
        product.setMaterialId(productRequset.getMaterialId());
        product.setDiscountId(productRequset.getDiscountId());

        // Thực hiện lưu đối tượng Product vào cơ sở dữ liệu hoặc thực hiện các xử lý khác tùy theo yêu cầu của bạn
         productRepository.save(product);
    }
}
