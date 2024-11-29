package com.example.trip_app.prices_service.application.commons.validators;

import com.example.trip_app.prices_service.application.ports.in.web.commands.CreatePriceCommand;
import com.example.trip_app.test_data.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceValidatorTest {

    private final TestDataFactory dataFactory = new TestDataFactory();

    @Test
    void returnsTrueWhenValidCommandWhenCanDeriveLocation() {
//        given
        CreatePriceCommand command = dataFactory.prepareValidPriceCommand();
//        when
        boolean validationResult = PriceValidator.canDeriveLocation(command);
//        then
        assertTrue(validationResult);
    }
    @Test
    void returnsFalseWhenCommandWithoutLocationWhenCanDeriveLocation() {
//        given
        CreatePriceCommand command = dataFactory.preparePriceCommandWithLocationAsNull();
//        when
        boolean validationResult = PriceValidator.canDeriveLocation(command);
//        then
        assertFalse(validationResult);
    }

    @Test
    void returnsFalseWhenCommandWithEmptyLocationWhenCanDeriveLocation() {
//        given
        CreatePriceCommand command = dataFactory.preparePriceCommandWithEmptyLocation();
//        when
        boolean validationResult = PriceValidator.canDeriveLocation(command);
//        then
        assertFalse(validationResult);
    }
}