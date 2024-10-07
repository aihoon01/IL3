package org.ecommercems.orderservice.controller;

import org.ecommercems.orderservice.proxy.ProductServiceProxy;
import org.ecommercems.orderservice.proxy.UserServiceProxy;
import org.ecommercems.orderservice.proxy.essentials.Product;
import org.ecommercems.orderservice.proxy.essentials.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")

public class OrderController {
    private final ProductServiceProxy productServiceProxy;
    private final UserServiceProxy userServiceProxy;
    public OrderController(
            UserServiceProxy userServiceProxy,
            ProductServiceProxy productServiceProxy
    ) {
        this.userServiceProxy = userServiceProxy;
        this.productServiceProxy = productServiceProxy;
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@Validated @RequestBody Order order) {
        Order newOrder = new Order(2);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(newOrder.getOrderId()).toUri();
        return ResponseEntity.created(location).body(newOrder);
    }

    @GetMapping("/{username}/{productId}")
    public ResponseEntity<Order> getOrder(
            @PathVariable String username,
            @PathVariable int productId) {
        Order order = new Order(2);

        //Update the order with user service
        User user = userServiceProxy.getUser(username);
        order.setCustomerId(user.getId());

        //Update the order with product service
        ResponseEntity<Product> product = productServiceProxy.getProduct(productId);
        order.setProductId(productId);
        return ResponseEntity.ok(order);
    }
}
