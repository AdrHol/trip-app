package com.example.trip_app.prices_service.application.domain.entities;

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
