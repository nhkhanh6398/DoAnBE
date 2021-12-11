package vku.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vku.project.entity.Customers;



public interface CustomerService {
    Page<Customers> finAll(Pageable pageable);
    Customers findById(int id);
    void saveCustomer(Customers customers);
    void updateCustomer(Customers customers);
    void delete(Customers customers);
}
