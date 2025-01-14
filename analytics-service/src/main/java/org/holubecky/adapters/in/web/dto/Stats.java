package org.holubecky.adapters.in.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private Double average;
    private Double max;
    private Double min;
    private int cheaper;
    private int moreExpansive;
}
