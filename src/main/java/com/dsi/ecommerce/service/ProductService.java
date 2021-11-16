package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.Shop;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDTO convertsProductEntityToProductDTO(Product product);

    Product convertProductDTOtoProductEntity(Product product, ProductDTO productDTO, Shop shop, MultipartFile image);

    void saveProduct(Product product);

    List<Product> getProducts();

    void deleteProduct(Product product);

    Product getProduct(Long shopId, Long productId) throws ResourceNotFoundException, ResourceNotFoundException;


}
