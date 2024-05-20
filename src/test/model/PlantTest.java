package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.PlantType.VEGETABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlantTest {
    private Plant testPlant;

    @BeforeEach

    public void runBefore() {
        testPlant = new Plant("testPlant", VEGETABLE,
                "information about testPlant");
    }

    @Test
    public void testConstructor() {
        assertEquals("testPlant", testPlant.getName());
        assertEquals(VEGETABLE, testPlant.getType());
        assertEquals("information about testPlant", testPlant.getInfo());
    }

    @Test
    public void testToJson() {
        JSONObject testJsonObject = testPlant.toJson();
        assertEquals("testPlant", testJsonObject.getString("name"));
        assertEquals(VEGETABLE, testJsonObject.get("type"));
        assertEquals("information about testPlant", testJsonObject.getString("info"));
    }
}