package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Product;
import java.util.List;

public interface ProductService {

    ProductDao convertsProductEntityToProductDTO(Product product);

    void saveProduct(Product product);

    List<Product> getProducts();

    void deleteProduct(Product product);

    Product getProductById(Long id) throws Exception;

    Product getProduct(Long shopId, Long productId) throws ResourceNotFoundException;


}
