package se.ifmo.programming.lab6.deserializers;

import com.google.gson.*;
import se.ifmo.programming.lab6.data.DragonCave;

import java.lang.reflect.Type;

public class CaveDeserializer implements JsonDeserializer<DragonCave> {
    @Override
    public DragonCave deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject deCave = new JsonObject();


        return null;
    }
}
