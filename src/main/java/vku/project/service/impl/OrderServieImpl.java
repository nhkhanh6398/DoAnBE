package vku.project.service.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
    @Autowired
    MailSender mailSender;
    @Override
    public void order(DtoOrder dtoOrder) {
        List<Product> productList = new ArrayList<>();
        Product product1 = null;
        Account account1 = accountRepository.findById(dtoOrder.getAccount()).orElse(null);
        StatusContract statusContract = new StatusContract(1,"Open");
        Date date = new Date(System.currentTimeMillis());
        Orders orders = new Orders(dtoOrder.getOrdersId(),date,dtoOrder.getAddress(),dtoOrder.getUserName(),dtoOrder.getPhone(),dtoOrder.getTotal(),statusContract,account1);
        Orders order1= this.orderProductRepository.save(orders);
        for(DtoProduct p : dtoOrder.getIdProduct()){
            product1 = productRepository.findById(p.getProductId()).orElse(null);
            productList.add(product1);
            if(account1!=null&&product1!=null){
                if (product1.getProductQuantity()>0){
                    product1.setProductQuantity(product1.getProductQuantity()-1);
                }
                List<Orders> ordersList = new ArrayList<>();
                ordersList.add(order1);
                order1.setProducts(productList);
                product1.setOrders(ordersList);
                productRepository.save(product1);
            }
        }
        String htmlMsg = "Xin chào: " + dtoOrder.getAccount() + "\n" + "Cảm ơn bạn đã mua hàng ở cửa hàng chúng tôi"  +
                "\n" + "Tống số tiền đơn hàng là: "+ dtoOrder.getTotal() +"\n";

        String subject = "Wellcome, you have successfully payment an account ad SieuThi24h";
        sendEmail("sieuthi24h.vku@gmail.com",account1.getCustomer().getEmail(),subject,htmlMsg);

    }

    @Override
    public List<Orders> getOrderByAccount(String account) {
        System.out.println();
        return this.orderProductRepository.findAllByAccount_Account(account);
    }

    public void sendEmail(String from,String to, String subject, String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }
}
