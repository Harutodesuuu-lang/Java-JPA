package co.istad.suiii.fswd.sbapp.controller;

import co.istad.suiii.fswd.sbapp.domain.Order;
import co.istad.suiii.fswd.sbapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }


    @PutMapping("/{id}")
    public Order update(@PathVariable UUID id, @RequestBody Order order) {
        return orderService.update(id, order);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        orderService.delete(id);
    }

    @GetMapping
    public Page<Order> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return orderService.getAll(PageRequest.of(page, size));
    }
}