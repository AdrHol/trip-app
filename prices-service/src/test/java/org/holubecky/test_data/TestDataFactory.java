package org.holubecky.test_data;


import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.test_data.utils.StringSequenceGenerator;

import java.util.Random;
import java.util.UUID;

public class TestDataFactory {

    private Random random = new Random();

    public CreatePriceCommand prepareValidPriceCommand(){
        String userId = UUID.randomUUID().toString();
        String product = StringSequenceGenerator.generateRandomString(4);
        String currency = StringSequenceGenerator.generateRandomString(3);
        long price = random.nextInt(100);
        String country = StringSequenceGenerator.generateRandomString(4);
        String city = StringSequenceGenerator.generateRandomString(5);
        String longitude = String.valueOf(random.nextInt(100));
        String latitude = String.valueOf(random.nextInt(100));

        CreatePriceCommand command = new CreatePriceCommand(userId, product, currency, price, city, country, longitude,latitude);
        return command;
    }
    public CreatePriceCommand preparePriceCommandWithLocationAsNull(){
        String userId = UUID.randomUUID().toString();
        String product = StringSequenceGenerator.generateRandomString(4);
        String currency = StringSequenceGenerator.generateRandomString(3);
        long price = random.nextInt(100);
        String country = null;
        String city = null;
        String longitude = null;
        String latitude = null;

        CreatePriceCommand command = new CreatePriceCommand(userId, product, currency, price, city, country, longitude,latitude);
        return command;
    }

    public CreatePriceCommand preparePriceCommandWithEmptyLocation(){
        String userId = UUID.randomUUID().toString();
        String product = StringSequenceGenerator.generateRandomString(4);
        String currency = StringSequenceGenerator.generateRandomString(3);
        long price = random.nextInt(100);
        String country = "";
        String city = "";
        String longitude = "";
        String latitude = "";

        CreatePriceCommand command = new CreatePriceCommand(userId, product, currency, price, city, country, longitude,latitude);
        return command;
    }

}
