package vku.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vku.project.dto.DtoProduct;
import vku.project.entity.*;
import vku.project.repository.CategoryRepository;
import vku.project.repository.ProductRepository;
import vku.project.repository.SupplierRepository;
import vku.project.service.CodeProductService;
import vku.project.service.ProductService;

import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CodeProductService codeProductService;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Page<Product> finAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Product findById(String id) {
        return this.productRepository.findById(id).orElse(null);
    }


    @Override
    public void saveProduct(DtoProduct product) {
        Suppliers suppliers = supplierRepository.getById(product.getSuppliersId());
        Categories categories = categoryRepository.getById(product.getCategoriesId());
        Product product1 = new Product(
                product.getProductId(), product.getProductName(), product.getProductQuantity(), product.getProductPrice(),
                product.getProductImage(), categories, suppliers);
        this.productRepository.save(product1);
        Status avaiable = new Status(1);
        for (int i = 0; i < product.getProductQuantity(); i++) {
            int n = 10000 + new Random().nextInt(90000);
            CodeProduct codeProduct = new CodeProduct(n, this.productRepository.findById(product.getProductId()).orElse(null),avaiable);
            product1.generateCode(codeProduct);
            codeProductService.save(codeProduct);
        }
        this.productRepository.save(product1);
    }

    @Override
    public void updateProduct(DtoProduct product) {
        Product product1 = this.productRepository.getById(product.getProductId());
        Categories categories = this.categoryRepository.getById(product.getCategoriesId());
        Suppliers suppliers = this.supplierRepository.getById(product.getSuppliersId());
        product1.setProductName(product.getProductName());
        product1.setProductQuantity(product.getProductQuantity());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductImage(product.getProductImage());
        product1.setCategories(categories);
        product1.setSuppliers(suppliers);
        this.productRepository.save(product1);
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<Product> searchByCategory(String name, Pageable pageable) {
        return productRepository.findAllByCategories(name, pageable);
    }
}
