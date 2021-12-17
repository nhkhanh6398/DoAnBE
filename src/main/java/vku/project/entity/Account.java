package vku.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    private String account;
    private String password;
    private Date dateCreate;
    @OneToOne(mappedBy = "account")
    private Customers customers;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Product> products;
    public Account() {
    }

    public Account( String account, String password, Date dateCreate, Customers customers) {

        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
        this.customers = customers;

    }

    public Account(String account, String password, Date dateCreate) {
        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
    }

    public Account( String account, String password, Date dateCreate, Customers customers, List<Product> products) {
        this.account = account;
        this.password = password;
        this.dateCreate = dateCreate;
        this.customers = customers;

        this.products = products;
    }




    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
