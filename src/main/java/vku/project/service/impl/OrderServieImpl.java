package vku.project.service.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vku.project.dto.DtoOrder;
import vku.project.dto.DtoProduct;
import vku.project.entity.*;
import vku.project.repository.AccountRepository;
import vku.project.repository.CodeProductRepository;
import vku.project.repository.OrderProductRepository;
import vku.project.repository.ProductRepository;
import vku.project.service.CodeProductService;
import vku.project.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServieImpl implements OrderService {
    @Autowired
    CodeProductRepository codeProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CodeProductService codeProductService;
    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public void order(DtoOrder dtoOrder) {
        System.out.println();
        for(DtoProduct p : dtoOrder.getIdProduct()){
            Product product1 = productRepository.findById(p.getProductId()).orElse(null);
            Account account1 = accountRepository.findById(dtoOrder.getAccount()).orElse(null);
            StatusContract statusContract = new StatusContract(1,"Open");
            Date date = new Date(System.currentTimeMillis());
            Orders orders = new Orders(dtoOrder.getOrdersId(),date,statusContract,account1);
            Orders order1= this.orderProductRepository.save(orders);
            if(account1!=null&&product1!=null){
                if (product1.getProductQuantity()>0){
                    product1.setProductQuantity(product1.getProductQuantity()-1);
                }
                List<Orders> ordersList = new ArrayList<>();
                ordersList.add(order1);
                product1.setOrders(ordersList);
                productRepository.save(product1);
            }
        }
    }
}
