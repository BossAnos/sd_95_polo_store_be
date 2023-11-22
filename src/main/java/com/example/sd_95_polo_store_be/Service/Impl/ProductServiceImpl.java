package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequest;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Model.Response.GetOneProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductForAdminResponse;
import com.example.sd_95_polo_store_be.Repository.*;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import com.example.sd_95_polo_store_be.Service.ProductService;
import com.example.sd_95_polo_store_be.common.Mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private EntityMapper entityMapper;
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private MatarialRepository matarialRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.entityMapper = new EntityMapper();
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductForAdminResponse> getAllProductForAdmin() {
        List<ProductForAdminResponse> product = productRepository.getAllProductByCreateDateDesc();
        for (ProductForAdminResponse productForAdminResponse : product) {
            var image = productRepository.getImage(productForAdminResponse.getId());
            productForAdminResponse.setImage(image);
        }

        return product;
    }

    @Override
    public void addProduct(ProductRequset productRequset) {
        if (productRequset.getName() == null || productRequset.getName().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }

        if (productRequset.getStatus() == null) {
            throw new IllegalArgumentException("Trạng thái sản phẩm không được để trống");
        }

        Products product = new Products();
//        product.setName(productRequset.getName());
//        product.setStatus(productRequset.getStatus());
//        product.setDescription(product.getDescription());
//        product.setCategoryId(product.getCategoryId());
//        product.setBrandId(productRequset.getBrandId());
//        product.setMaterialId(productRequset.getMaterialId());
//        product.setDiscountId(productRequset.getDiscountId());

        // Thực hiện lưu đối tượng Product vào cơ sở dữ liệu hoặc thực hiện các xử lý khác tùy theo yêu cầu của bạn
        productRepository.save(product);
    }

    @Override
    public GetOneProductResponse getOne(Long id) {
        var product = productRepository.getId(id).orElseThrow();
        product.setProductDetails(productDetailService.getForProduct(id));
        return product;
    }

    @Override
    public void create(ProductRequest productRequest) {
        var now = OffsetDateTime.now();
        var category = categoriesRepository.findById(productRequest.getCategoryId()).orElseThrow();
        var brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow();
        var material = matarialRepository.findById(productRequest.getMaterialId()).orElseThrow();
        Products products = new Products();
        products.setName(productRequest.getName());
        products.setStatus(1);
        products.setDescription(productRequest.getDescription());
        products.setCreateDate(now);
        products.setUpdatedAt(now);
        products.setCategories(category);
        products.setBrands(brand);
        products.setMaterials(material);
        productRepository.save(products);
    }

}
