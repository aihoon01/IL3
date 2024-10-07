package org.ecommercems.orderservice.proxy;

import org.ecommercems.orderservice.proxy.essentials.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductServiceProxy {
    @PostMapping("/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product);

    @GetMapping("/products" +
            "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id);
}
