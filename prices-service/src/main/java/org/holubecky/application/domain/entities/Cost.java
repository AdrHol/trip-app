package org.holubecky.application.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cost {
    private String currency;
    private long price;
}
