package org.holubecky.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.in.web.exceptions.InvalidCommandException;
import org.holubecky.adapters.out.persistance.repository.ElasticSearchRepository;
import org.holubecky.adapters.out.persistance.repository.entity.ProductEntity;
import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.CreateProductUseCase;
import org.holubecky.application.ports.in.web.FetchProductUseCase;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.in.web.dto.ProductRequestResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductsController {

    private final CreateProductUseCase createProductUseCase;
    private final ElasticSearchRepository elasticsearchOperations;
    private final FetchProductUseCase fetchProductUseCase;

    @GetMapping("/id")
    public ResponseEntity<ProductRequestResponse> fetchProductsById(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(fetchProductUseCase.getProductById(id));
    }

    @GetMapping("/data")
    public ResponseEntity<ProductRequestResponse> fetchProductsByDescription(@RequestParam(name = "title", required = false) String title,
                                                                             @RequestParam(name = "description", required = false) String description){
        if(title == null && description == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(fetchProductUseCase.getProductByTitleAndOrDescription(Optional.ofNullable(title), Optional.ofNullable(description)));
    }

    @PostMapping()
    public ResponseEntity<ProductRequestResponse> fetchSimilarProductsInArea(@RequestBody ProductCreationRequest productCreationRequest){
        if(!productCreationRequest.hasCityAndCountry() && !productCreationRequest.hasCoordinatesFilled()){
            throw new InvalidCommandException();
        }
        return ResponseEntity.ok(createProductUseCase.fetchSimilarProducts(productCreationRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> result = ((PageImpl<ProductEntity>) elasticsearchOperations.findAll()).stream().toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductRequestResponse> addProduct(@RequestBody ProductCreationRequest productCreationRequest){
        if(!productCreationRequest.hasCityAndCountry() && !productCreationRequest.hasCoordinatesFilled()){
            throw new InvalidCommandException();
        }

        return ResponseEntity.ok(createProductUseCase.addNewProduct(productCreationRequest));
    }
}
