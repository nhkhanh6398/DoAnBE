package vku.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vku.project.entity.Suppliers;
import vku.project.repository.SupplierRepository;
import vku.project.service.SupplierService;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<Suppliers> finAll() {
        return this.supplierRepository.findAll();
    }

    @Override
    public Suppliers findById(int id) {
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSuppliers(Suppliers suppliers) {
    this.supplierRepository.save(suppliers);
    }

    @Override
    public void delete(Suppliers suppliers) {
    this.supplierRepository.delete(suppliers);
    }
}
