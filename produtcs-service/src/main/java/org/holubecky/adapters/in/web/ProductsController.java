package org.holubecky.adapters.in.web;

import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductsController {


    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test response");
    }

}
