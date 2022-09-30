package se.ifmo.programming.lab7.deserializers;

import com.google.gson.*;
import se.ifmo.programming.lab7.data.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject deCoordinates = jsonElement.getAsJsonObject();
        Coordinates coordinates = new Coordinates(deCoordinates.get("x").getAsLong(),
                deCoordinates.get("y").getAsDouble());
        return coordinates;
    }
}
