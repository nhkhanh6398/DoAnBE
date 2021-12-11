package vku.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vku.project.entity.Employee;
import vku.project.entity.Suppliers;

import java.util.List;


public interface SupplierService {
    List<Suppliers> finAll();
    Suppliers findById(int id);
    void saveSuppliers(Suppliers suppliers);
    void delete(Suppliers suppliers);
}
