package vku.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vku.project.dto.DtoCustomer;
import vku.project.entity.Customers;



public interface CustomerService {
    Page<Customers> finAll(Pageable pageable);
    Customers findById(String id);
    void saveCustomer(DtoCustomer customers);
    void updateCustomer(DtoCustomer customers);
    void delete(Customers customers);
    Page<Customers> searchCustomer(String name, Pageable pageable);
}
