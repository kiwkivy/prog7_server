package se.ifmo.programming.lab6.deserializers;

import com.google.gson.*;
import se.ifmo.programming.lab6.data.*;

import java.lang.reflect.Type;

public class DragonDeserializer implements JsonDeserializer<Dragon> {

    @Override
    public Dragon deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject deDragon = jsonElement.getAsJsonObject();
        String name = deDragon.get("name").getAsString();
        Coordinates coordinates = jsonDeserializationContext.deserialize(deDragon.get("coordinates"), Coordinates.class);
        int age = deDragon.get("age").getAsInt();
        Color color = Color.valueOf(deDragon.get("color").getAsString());
        DragonType dragonType = DragonType.valueOf(deDragon.get("type").getAsString());
        DragonCharacter character = DragonCharacter.valueOf(deDragon.get("character").getAsString());
        DragonCave cave = jsonDeserializationContext.deserialize(deDragon.get("cave"), DragonCave.class);

        Dragon dragon = new Dragon(name, coordinates, age, color, dragonType, character, cave);

        return dragon;
    }
}
