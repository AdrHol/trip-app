package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;


public interface NewPriceUseCase {

    Price createPriceUseCase(CreatePriceCommand createPriceCommand);
}
