package org.holubecky.application.commons.validators;



import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;

import java.util.function.Predicate;

public class PriceValidator {

//    public static Predicate<CreatePriceCommand> isLocationPresent = createPriceCommand ->
//            createPriceCommand.c != null
//                    && !createPriceCommand.city().isEmpty()
//                    && createPriceCommand.country() != null
//                    && !createPriceCommand.country().isEmpty();
//    public static Predicate<CreatePriceCommand> areCoordinatesPreset = createPriceCommand ->
//            createPriceCommand.latitude() != null
//                    && !createPriceCommand.latitude().isEmpty()
//                    && createPriceCommand.longitude() != null
//                    && !createPriceCommand.longitude().isEmpty();
//
//    public static boolean canDeriveLocation(CreatePriceCommand createPriceCommand){
//        return isLocationPresent.test(createPriceCommand) || areCoordinatesPreset.test(createPriceCommand);
//    }

}
