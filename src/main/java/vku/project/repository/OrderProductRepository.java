package vku.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vku.project.entity.Orders;

public interface OrderProductRepository extends JpaRepository<Orders,Integer> {
}
