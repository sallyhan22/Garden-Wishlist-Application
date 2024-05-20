package persistence;

import model.Garden;
import model.Plant;
import model.PlantType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Garden g = new Garden("sally");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGarden() {
        try {
            Garden g = new Garden("sally");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGarden.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGarden.json");
            g = reader.read();
            assertEquals("sally", g.getName());
            assertEquals(0, g.showGarden().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGarden() {
        try {
            Garden g = new Garden("sally");
            g.addPlant(new Plant("tomato", PlantType.FRUIT, "tomatoInfo"));
            g.addPlant(new Plant("garlic", PlantType.VEGETABLE, "garlicInfo"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGarden.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGarden.json");
            g = reader.read();
            assertEquals("sally", g.getName());
            List<Plant> plants = g.showGarden();
            assertEquals(2, plants.size());
            checkPlant("tomato", PlantType.FRUIT, "tomatoInfo", plants.get(0));
            checkPlant("garlic", PlantType.VEGETABLE, "garlicInfo", plants.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
