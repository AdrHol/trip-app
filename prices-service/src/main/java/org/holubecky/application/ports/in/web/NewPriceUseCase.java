package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.web.dto.PriceDTO;


public interface NewPriceUseCase {

    PriceDTO createPriceUseCase(CreatePriceCommand createPriceCommand);
}
