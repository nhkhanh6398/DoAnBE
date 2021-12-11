package vku.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vku.project.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
