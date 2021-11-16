package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.ProductService;
import com.dsi.ecommerce.utility.FileUpload;
import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDTO convertsProductEntityToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setAvailableQuantity(product.getAvailableQuantity());
        return productDTO;
    }

    @Override
    public Product convertProductDTOtoProductEntity(Product product, ProductDTO productDTO, Shop shop, MultipartFile image){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setAvailableQuantity(productDTO.getAvailableQuantity());
        product.setImage(FileUpload.saveImage(ImageType.PRODUCT_IMAGE, productDTO.getName(), image));
        product.setShop(shop);
        product.setSoldItems(product.getSoldItems()!=null? product.getSoldItems() : 0);
        return product;
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
        return productDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Product id: " + id));
    }
}
