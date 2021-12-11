package vku.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String account;
    private String password;
    private Date dateCreate;
    @OneToOne(mappedBy = "account")
    private Customers customers;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Product> products;
    public Account() {
    }

    public Account(int id, String account, String password, Date dateCreate, Customers customers, List<Role> roles) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
        this.customers = customers;
        this.roles = roles;
    }

    public Account(int id, String account, String password, Date dateCreate, Customers customers, List<Role> roles, List<Product> products) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
        this.customers = customers;
        this.roles = roles;
        this.products = products;
    }

    public Account(int id, String account, String password, Date dateCreate, Customers customers) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
        this.customers = customers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
