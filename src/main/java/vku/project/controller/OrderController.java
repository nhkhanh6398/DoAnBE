package vku.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vku.project.dto.DtoOrder;
import vku.project.entity.Account;
import vku.project.entity.OrderProduct;
import vku.project.entity.Orders;
import vku.project.entity.Product;
import vku.project.repository.AccountRepository;
import vku.project.service.OrderProductService;
import vku.project.service.OrderService;
import vku.project.service.ProductService;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/orders")
    public ResponseEntity<?> order(@RequestBody DtoOrder order) {
        System.out.println();
        this.orderService.order(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listOrder-Admin")
    public ResponseEntity<Page<OrderProduct>> finall(@PageableDefault(size = 5) Pageable pageable) {
        Page<OrderProduct> orderProducts = this.orderProductService.findAllOrder(pageable);
        if (orderProducts == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }

    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable String id) {
        System.out.println();
        List<Orders> orders = this.orderService.getOrderByAccount(id);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getListOrderProduct/{account}")
    public ResponseEntity<List<OrderProduct>> list(@PathVariable String account) {
        List<OrderProduct> list = this.orderProductService.findAll(account);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<OrderProduct>> search(@RequestParam("key") String name, @PageableDefault(size = 5) Pageable pageable) {
        System.out.println();
        Page<OrderProduct> orderProducts = this.orderProductService.search(name, pageable);
        if (orderProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }

    @GetMapping("/statisticOrder")
    public ResponseEntity<List<Orders>> listResponseEntity(@RequestParam(value = "start", required = false) String start,
                                                           @RequestParam(value = "end", required = false) String end) throws ParseException {
        if (start == null || end == null || start.equals("") || end.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
        List<Orders> ordersList = this.orderService.getListOrder(startDate, endDate);
        if (ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }
}
