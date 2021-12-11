package vku.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int suppliersId;
    @NotNull
    private String suppliersName;
    @NotNull
    private String phone;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "suppliers")
    @JsonIgnore
    Set<Product> products;

    public Suppliers() {
    }

    public Suppliers(int suppliersId, String suppliersName, String phone, String address, Set<Product> products) {
        this.suppliersId = suppliersId;
        this.suppliersName = suppliersName;
        this.phone = phone;
        this.address = address;
        this.products = products;
    }

    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getSuppliersName() {
        return suppliersName;
    }

    public void setSuppliersName(String suppliersName) {
        this.suppliersName = suppliersName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
