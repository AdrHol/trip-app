package org.holubecky.adapters.out.persistance.repositories;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.holubecky.application.domain.entities.Cost;

import java.time.LocalDateTime;


@Entity @Table(name = "prices")
@Getter @Setter
@NoArgsConstructor
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String productId;
    private LocalDateTime postedAt;
    @Embedded
    private Cost cost;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}
