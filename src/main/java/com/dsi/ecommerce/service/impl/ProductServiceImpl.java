package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDao convertsProductEntityToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setAvailableQuantity(product.getAvailableQuantity());
        return null;
    }

    @Override
    public void saveProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        return productDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Movie id: " + id));
    }
}
