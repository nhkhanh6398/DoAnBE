package vku.project.dto;

import vku.project.entity.Product;

import java.util.List;

public class DtoOrder {
    private int ordersId;
    private List<DtoProduct> idProduct;
    private String account;


    public DtoOrder() {
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public DtoOrder(int ordersId, List<DtoProduct> idProduct, String account) {
        this.ordersId = ordersId;
        this.idProduct = idProduct;
        this.account = account;
    }

    public List<DtoProduct> getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(List<DtoProduct> idProduct) {
        this.idProduct = idProduct;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
