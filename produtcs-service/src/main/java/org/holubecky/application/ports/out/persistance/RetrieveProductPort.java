package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.model.Product;
import org.springframework.data.elasticsearch.core.IndexInformation;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RetrieveProductPort {

    List<IndexInformation> getProducts();
    List<Product> fetchProductsBySimilarDescription(String title, String description);
    Optional<Product> fetchProductById(String id);
    List<Product> autocompleteTitle(String title);

}
