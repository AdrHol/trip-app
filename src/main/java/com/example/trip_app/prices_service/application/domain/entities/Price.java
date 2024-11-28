package com.example.trip_app.prices_service.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@NoArgsConstructor
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String product;
    private LocalDateTime postedAt;
    @Embedded
    private Cost cost;
    @Embedded
    private Location location;
}
