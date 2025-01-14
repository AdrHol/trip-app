package org.holubecky.adapters.in.web.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsDTO {

    private Stats locally;
    private Stats overall;

}
