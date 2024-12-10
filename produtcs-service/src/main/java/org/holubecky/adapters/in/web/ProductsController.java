package org.holubecky.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repository.ElasticSearchRepository;
import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.ports.in.web.CreateProductUseCase;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductsController {

    private final CreateProductUseCase createProductUseCase;
    private final ElasticSearchRepository elasticsearchOperations;

    @PostMapping("/similar")
    public ResponseEntity<List<ProductEntity>> fetchSimilarProducts(@RequestBody ProductCreationRequest productCreationRequest){
        return ResponseEntity.ok(createProductUseCase.fetchSimilarProducts(productCreationRequest));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductEntity> addProduct(){
        return ResponseEntity.ok(new ProductEntity());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> result = ((PageImpl<ProductEntity>) elasticsearchOperations.findAll()).stream().toList();
        return ResponseEntity.ok(result);
    }

}
