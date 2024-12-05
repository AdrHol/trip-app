package org.holubecky.adapters.in.web;

import feign.Response;

import org.holubecky.adapters.out.persistance.ElasticSearchAdapter;
import org.holubecky.application.domain.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductsController {

    @Autowired
    private ElasticSearchAdapter elasticSearchAdapter;

    @GetMapping
    public ResponseEntity<ProductEntity> test(){
        ProductEntity entity = new ProductEntity();
        entity.setTitle("test");
        entity.setDescription("test");

        ProductEntity result = elasticSearchAdapter.saveProduct(entity);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<IndexInformation>> getProducts(){
        return ResponseEntity.ok(elasticSearchAdapter.getProducts());
    }

}
