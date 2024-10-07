package org.ecommercems.orderservice.proxy;

import org.ecommercems.orderservice.proxy.essentials.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username);
}
