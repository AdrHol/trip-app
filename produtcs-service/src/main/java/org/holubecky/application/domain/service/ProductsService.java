package org.holubecky.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.domain.model.Location;
import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.CreateProductUseCase;
import org.holubecky.application.ports.in.web.FetchProductUseCase;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.in.web.dto.ProductDTO;
import org.holubecky.application.ports.in.web.dto.ProductRequestResponse;
import org.holubecky.application.ports.in.web.dto.RequestStatus;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.holubecky.application.ports.out.web.GeoCodingPort;
import org.holubecky.configuration.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService implements CreateProductUseCase, FetchProductUseCase {

    private final CreateProductPort createProductPort;
    private final GeoCodingPort geoCodingPort;
    private final RetrieveProductPort retrieveProductPort;
    private final ProductMapper mapper;


    @Override
    public ProductRequestResponse fetchSimilarProducts(ProductCreationRequest productCreationRequest) {
        Product requestedProduct = prepareQueryProduct(productCreationRequest);
//        checkLocationData(requestedProduct, productCreationRequest);
        List<ProductDTO> result = createProductPort.fetchSimilarProducts(requestedProduct).stream()
                .map(mapper::mapProductToDto).toList();
        RequestStatus status = result.isEmpty() ? RequestStatus.ENTITY_NOT_FOUND : RequestStatus.ENTITY_FOUND;

        return ProductRequestResponse.builder().response(status.get()).body(result).build();
    }

    @Override
    public ProductRequestResponse addNewProduct(ProductCreationRequest productCreationRequest) {
        Product newProduct = mapper.mapCreationRequestToModel(productCreationRequest);
//        checkLocationData(newProduct, productCreationRequest);
        List<ProductDTO> body = new ArrayList<>();
        Product created = createProductPort.saveProduct(newProduct);
        RequestStatus status = RequestStatus.ENTITY_NOT_FOUND;

        if(created.getId() != null){
            body.add(mapper.mapProductToDto(created));
            status = RequestStatus.ENTITY_FOUND;
        }

        return ProductRequestResponse.builder()
                .response(status.get()).body(body).build();
    }
    @Override
    public ProductRequestResponse getProductById(String id) {
        Optional<Product> searchResult = retrieveProductPort.fetchProductById(id);
        RequestStatus status = RequestStatus.ENTITY_NOT_FOUND;
        List<ProductDTO> body = new ArrayList<>();

        if(searchResult.isPresent()) {
            status = RequestStatus.ENTITY_FOUND;
            body.add(mapper.mapProductToDto(searchResult.get()));
        }
        return ProductRequestResponse.builder()
                .response(status.get())
                .body(body)
                .build();
    }

    @Override
    public ProductRequestResponse getProductByTitleAndOrDescription(Optional<String> title, Optional<String> description) {
        List<Product> searchResult = retrieveProductPort.fetchProductsBySimilarDescription(title.orElse(""), description.orElse(""));
        return buildResponse(searchResult);
    }

    @Override
    public ProductRequestResponse getProductByAutocompleteTitle(String title) {
        List<Product> searchResult = retrieveProductPort.autocompleteTitle(title);
        return buildResponse(searchResult);
    }

    private Product prepareQueryProduct(ProductCreationRequest productCreationRequest){
        return Product.builder()
                .description(productCreationRequest.getDescription())
                .title(productCreationRequest.getTitle())
//                .location(Location.builder().lon(productCreationRequest.getLon()).lat(productCreationRequest.getLat())
//                        .country(productCreationRequest.getCountry()).city(productCreationRequest.getCity()).build())
                .build();
    }
//    private void checkLocationData(Product domainObject, ProductCreationRequest productCreationRequest){
//        if(productCreationRequest.hasCityAndCountry() && productCreationRequest.hasCoordinatesFilled()) {
//            return;
//        }
//
//        Location newLocation = productCreationRequest.hasCoordinatesFilled() ?
//                geoCodingPort.getLocationByCoordinates(productCreationRequest.getLon(), productCreationRequest.getLat())
//                : geoCodingPort.getCoordinatesByLocation(productCreationRequest.getCity(), productCreationRequest.getCountry());
//        domainObject.setLocation(newLocation);
//    }

    private ProductRequestResponse buildResponse(List<Product> searchResult){
        List<ProductDTO> body = searchResult.stream().map(mapper::mapProductToDto).toList();
        RequestStatus status = searchResult.isEmpty() ? RequestStatus.ENTITY_NOT_FOUND : RequestStatus.ENTITY_FOUND;
        return ProductRequestResponse.builder().response(status.get()).body(body).build();
    }

}
