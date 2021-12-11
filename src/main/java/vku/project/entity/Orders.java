package vku.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ordersId;
    @NotNull
    private String orderDate;
    @NotNull
    private String requireDate;
    @NotNull
    private String status;

    @ManyToMany(mappedBy = "orders")
    @JsonIgnore
    List<Product> products;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    @JsonIgnore
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customers customer;


    public Orders() {
    }

    public Orders(int ordersId, @NotNull String orderDate, @NotNull String requireDate, @NotNull String status, List<Product> products, Employee employee, Customers customer) {
        this.ordersId = ordersId;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.status = status;
        this.products = products;
        this.employee = employee;
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
