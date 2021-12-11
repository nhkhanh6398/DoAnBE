package vku.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vku.project.entity.Customers;
import vku.project.repository.CustomerRepository;
import vku.project.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customers> finAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customers findById(int id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCustomer(Customers customers) {
        this.customerRepository.save(customers);
    }

    @Override
    public void updateCustomer(Customers customers) {
        Customers customers1 = this.customerRepository.getById(customers.getIdCustomer());
        customers1.setNameCustomer(customers.getNameCustomer());
        customers1.setPhone(customers.getPhone());
        customers1.setEmail(customers.getEmail());
        customers1.setIdCard(customers.getIdCard());
        customers1.setAddress(customers.getAddress());
        this.customerRepository.save(customers1);
    }

    @Override
    public void delete(Customers customers) {
        customerRepository.delete(customers);
    }


}
