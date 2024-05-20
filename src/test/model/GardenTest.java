package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.PlantType.*;
import static org.junit.jupiter.api.Assertions.*;

public class GardenTest {
    private Garden testGarden;
    private Plant p1;
    private Plant p2;
    private Plant p3;
    private Plant p4;

    @BeforeEach

    public void runBefore() {
        testGarden = new Garden("sally");
        p1 = new Plant("Tomato", FRUIT, "testTomatoInfo");
        p2 = new Plant("Parsley", HERB, "testParsleyInfo");
        p3 = new Plant("Garlic", VEGETABLE, "testGarlicInfo");
        p4 = new Plant("Apple", FRUIT, "testAppleInfo");


    }

    @Test
    public void testConstructor() {
        assertEquals("sally", testGarden.getName());
        assertTrue(testGarden.showGarden().isEmpty());
    }

    @Test
    public void testAddSinglePlant() {
        testGarden.addPlant(p1);
        List<Plant> plantsInGarden = testGarden.showGarden();
        assertEquals(1, plantsInGarden.size());
        assertEquals(p1, plantsInGarden.get(0));
    }

    @Test
    public void testAddMultiplePlants() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p2);
        List<Plant> plantsInGarden = testGarden.showGarden();
        assertEquals(2, plantsInGarden.size());
        assertEquals(p1, plantsInGarden.get(0));
        assertEquals(p2, plantsInGarden.get(1));
    }


    @Test
    public void testRemoveSinglePlant() {
        testGarden.addPlant(p1);
        List<Plant> plantsInGarden = testGarden.showGarden();
        testGarden.removePlant("Tomato");
        assertEquals(0, plantsInGarden.size());
    }


    @Test
    public void testRemoveMultiplePlants() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p2);
        testGarden.addPlant(p3);
        testGarden.addPlant(p4);
        testGarden.removePlant("Garlic");
        testGarden.removePlant("Parsley");
        List<Plant> plantsInGarden = testGarden.showGarden();
        assertEquals(2, plantsInGarden.size());
        assertEquals(p1, plantsInGarden.get(0));
        assertEquals(p4, plantsInGarden.get(1));
    }

    @Test
    public void testRemoveNotInGarden() {
        testGarden.addPlant(p1);
        List<Plant> plantsInGarden = testGarden.showGarden();
        testGarden.removePlant("Cherry");
        assertEquals(1, plantsInGarden.size());
        assertEquals(p1, plantsInGarden.get(0));
    }

    @Test
    public void testFindPlantInfo() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p2);
        assertEquals("testParsleyInfo", testGarden.findPlantInfo("Parsley"));
    }

    @Test
    public void testFindPlantInfoNotInGarden() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p2);
        assertNull(testGarden.findPlantInfo("Potato"));
    }

    @Test
    public void testCountNumPlantTypes() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p3);
        testGarden.addPlant(p1);
        testGarden.addPlant(p4);
        assertEquals(3, testGarden.countNumPlantTypes(FRUIT));
    }

    @Test
    public void testCountNumPlantTypesNoneInGarden() {
        testGarden.addPlant(p1);
        testGarden.addPlant(p3);
        testGarden.addPlant(p1);
        testGarden.addPlant(p4);
        assertEquals(0, testGarden.countNumPlantTypes(HERB));
    }

    @Test
    public void testShowPlantNames() {
        testGarden.addPlant(p3);
        testGarden.addPlant(p4);
        testGarden.addPlant(p1);
        List<String> plantNames = testGarden.showPlantNames();
        assertEquals(3, plantNames.size());
        assertEquals("Garlic", plantNames.get(0));
        assertEquals("Apple", plantNames.get(1));
        assertEquals("Tomato", plantNames.get(2));
    }

    @Test
    public void testToJson() {
        testGarden.addPlant(p3);
        testGarden.addPlant(p4);
        JSONObject testJsonObject = testGarden.toJson();
        JSONArray testJsonArray = testJsonObject.getJSONArray("plants");
        assertFalse(testJsonArray.isEmpty());
        assertEquals("Garlic", testJsonArray.getJSONObject(0).getString("name"));
        assertEquals(VEGETABLE, testJsonArray.getJSONObject(0).get("type"));
        assertEquals("testGarlicInfo", testJsonArray.getJSONObject(0).getString("info"));
        assertEquals("Apple", testJsonArray.getJSONObject(1).getString("name"));
        assertEquals(FRUIT, testJsonArray.getJSONObject(1).get("type"));
        assertEquals("testAppleInfo", testJsonArray.getJSONObject(1).getString("info"));
    }

}
