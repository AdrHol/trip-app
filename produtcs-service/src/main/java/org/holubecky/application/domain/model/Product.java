package org.holubecky.application.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String title;
    private String description;
    private Location location;
}
