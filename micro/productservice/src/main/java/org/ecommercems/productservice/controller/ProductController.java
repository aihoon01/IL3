package org.ecommercems.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = new Product(1, "Water", 22.02, 100);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(location).body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product newProduct = new Product(1, "Water", 22.02, 100);
        System.out.println("new Product: " + newProduct + "\nRequest id: " + id);
        return newProduct.getId() == id ?
        ResponseEntity.ok(newProduct):
        ResponseEntity.notFound().build();
    }
}
