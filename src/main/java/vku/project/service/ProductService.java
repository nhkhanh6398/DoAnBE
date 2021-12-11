package vku.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vku.project.dto.DtoProduct;
import vku.project.entity.Product;


public interface ProductService {
    Page<Product> finAll(Pageable pageable);
    Product findById(String id);
    void saveProduct(DtoProduct product);
    void updateProduct(DtoProduct product);
    void delete(String id);
    Page<Product> searchByCategory(String name, Pageable pageable);
}
