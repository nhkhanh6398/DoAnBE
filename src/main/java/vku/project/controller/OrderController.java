package vku.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vku.project.dto.DtoOrder;
import vku.project.entity.Account;
import vku.project.entity.Orders;
import vku.project.entity.Product;
import vku.project.repository.AccountRepository;
import vku.project.service.OrderService;
import vku.project.service.ProductService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/orders")
    public ResponseEntity<?> order(@RequestBody DtoOrder order){
        System.out.println();
        this.orderService.order(order);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable String id){
        System.out.println();
        List<Orders> orders = this.orderService.getOrderByAccount(id);
        if (orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
