package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.model.Product;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ProductService {
    ProductDao convertsProductEntityToProductDTO(Product product);
    void saveProduct(Product product);
    List<Product> getProducts();
    void deleteProduct(Product product);
    Product getProductById(Long id) throws Exception;
}
