package org.holubecky.application.domain.entities;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cost {
    private String currency;
    private long price;
}
