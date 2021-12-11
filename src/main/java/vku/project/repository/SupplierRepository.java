package vku.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vku.project.entity.Suppliers;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Integer> {
}
