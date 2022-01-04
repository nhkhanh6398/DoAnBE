package vku.project.service;

import org.hibernate.criterion.Order;
import vku.project.dto.DtoOrder;
import vku.project.entity.Account;
import vku.project.entity.Orders;
import vku.project.entity.Product;

import java.util.List;

public interface OrderService {
    void order(DtoOrder dtoOrder);
    List<Orders> getOrderByAccount(String account);
}
