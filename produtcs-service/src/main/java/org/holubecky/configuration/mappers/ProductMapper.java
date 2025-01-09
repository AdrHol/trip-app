package org.holubecky.configuration.mappers;

import ch.qos.logback.core.util.StringUtil;
import org.holubecky.adapters.out.persistance.repository.entity.LocationEntity;
import org.holubecky.adapters.out.persistance.repository.entity.ProductEntity;
import org.holubecky.application.domain.model.Location;
import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.dto.LocationDTO;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.in.web.dto.ProductDTO;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductEntity mapDomainObjectToEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        String id = product.getId();

        if(StringUtil.isNullOrEmpty(id)) {
            productEntity.setId(id);
        }
        productEntity.setTitle(product.getTitle());
        productEntity.setTitle_as_type(product.getTitle());

        productEntity.setDescription(product.getDescription());
//        productEntity.setLocationEntity(
//                new LocationEntity(product.getLocation().getCity(),
//                        product.getLocation().getCountry(),
//                        new GeoPoint(product.getLocation().getLat(),
//                                product.getLocation().getLon())));

        return productEntity;
    }

    public Product mapEntityToDomain(ProductEntity productEntity){
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setDescription(productEntity.getDescription());
//        product.setLocation(Location.builder().city(productEntity.getLocationEntity().getCity())
//                .country(productEntity.getLocationEntity().getCountry())
//                .lat(productEntity.getLocationEntity().getCoordinates().getLat())
//                .lon(productEntity.getLocationEntity().getCoordinates().getLon()).build());
        return product;
    }

    public ProductDTO mapProductToDto(Product product){
        return ProductDTO.builder().id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
//                .location(LocationDTO.builder().city(product.getLocation().getCity())
//                        .country(product.getLocation().getCountry())
//                        .lat(product.getLocation().getLat())
//                        .lon(product.getLocation().getLon())
//                        .build())
                .build();
    }
    public Product mapCreationRequestToModel(ProductCreationRequest productCreationRequest){
        return Product.builder()
                .title(productCreationRequest.getTitle())
                .description(productCreationRequest.getDescription())
//                .location(Location.builder().city(productCreationRequest.getCity())
//                        .country(productCreationRequest.getCountry())
//                        .lat(productCreationRequest.getLat())
//                        .lon(productCreationRequest.getLon()).build())
                .build();
    }
}
