package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entity.ProductEntity;
import org.springframework.data.elasticsearch.core.IndexInformation;

import java.util.List;

public interface RetrieveProductPort {

    List<IndexInformation> getProducts();

}