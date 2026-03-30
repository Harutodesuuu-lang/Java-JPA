package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.domain.Order;
import co.istad.suiii.fswd.sbapp.domain.OrderDetail;
import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.repository.OrderRepository;
import co.istad.suiii.fswd.sbapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public Order create(Order order) {
        order.setOrderedAt(Instant.now());
        order.setIsDeleted(false);

        if (order.getOrderDetail() != null) {
            for (OrderDetail detail : order.getOrderDetail()) {

                Product product = productRepository.findByCode(detail.getProductCode());

                if (product == null) {
                    throw new RuntimeException("Product not found");
                }

                detail.setProduct(product);
                detail.setOrder(order);
                detail.setUnitPrice(product.getPrice());
                detail.setDiscount(0f);
            }
        }

        return orderRepository.save(order);
    }


    public Order update(UUID id, Order newOrder) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setAddress(newOrder.getAddress());
        order.setOrderedBy(newOrder.getOrderedBy());


        order.getOrderDetail().clear();


        if (newOrder.getOrderDetail() != null) {
            for (OrderDetail detail : newOrder.getOrderDetail()) {

                Product product = productRepository.findByCode(detail.getProductCode());

                if (product == null) {
                    throw new RuntimeException("Product not found");
                }

                detail.setProduct(product);
                detail.setOrder(order);

                detail.setUnitPrice(product.getPrice());
                detail.setDiscount(0f);

                order.getOrderDetail().add(detail);
            }
        }

        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setIsDeleted(true);
        orderRepository.save(order);
    }


    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findByIsDeletedFalseOrderByOrderedAtDesc(pageable);
    }
}