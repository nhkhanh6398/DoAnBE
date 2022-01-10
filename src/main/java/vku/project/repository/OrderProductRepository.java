package vku.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vku.project.entity.OrderProduct;
import vku.project.entity.OrderProductKey;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
    @Query(
            value = "select * from order_product inner join orders on orders.orders_id = order_product.order_id \n" +
                    "where orders.account like %:key%" +
                    "group by orders.orders_id",nativeQuery = true
            )
    Page<OrderProduct> listOrderProductByAccount(@Param("key") String account, Pageable pageable);

    List<OrderProduct> findAllByOrders_Account_Account(String account);
    Page<OrderProduct> findOrderProductByOrdersAccount_Account(String account, Pageable pageable);
}
