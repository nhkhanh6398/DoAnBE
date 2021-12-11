package vku.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Employee {
    @Id
    private String id;
    @NotNull
    private String nameEmployee;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private String idCard;
    @NotNull
    private double salary;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String level;
    private String avatar;

    @OneToMany(mappedBy = "employee")
    private Set<Orders> orders;

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "positionId")
    private Position position;

    public Employee() {
    }

    public Employee(String id, @NotNull String nameEmployee, @NotNull Date dateOfBirth, @NotNull String idCard, @NotNull double salary, @NotNull String phoneNumber, @NotNull String email, @NotNull String address, @NotNull String level, String avatar, Position position) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.dateOfBirth = dateOfBirth;
        this.idCard = idCard;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.level = level;
        this.avatar = avatar;
        this.position = position;
    }

    public Employee(String id, String nameEmployee, Date dateOfBirth, String idCard, double salary, String phoneNumber, String email, String address, String level, String avatar, Set<Orders> orders, Position position) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.dateOfBirth = dateOfBirth;
        this.idCard = idCard;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.level = level;
        this.avatar = avatar;
        this.orders = orders;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String idEmployee) {
        this.id = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
