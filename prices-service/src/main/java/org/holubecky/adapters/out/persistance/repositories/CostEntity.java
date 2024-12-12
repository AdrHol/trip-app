package org.holubecky.adapters.out.persistance.repositories;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CostEntity {
    private String currency;
    private long price;
}
