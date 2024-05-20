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
import static org.junit.jupiter.api.Assertions.*;


// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


public class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Garden g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
// pass
        }
    }


    @Test
    void testReaderEmptyGarden() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGarden.json");
        try {
            Garden g = reader.read();
            assertEquals("sally", g.getName());
            assertEquals(0, g.showGarden().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralGarden() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGarden.json");
        try {
            Garden g = reader.read();
            assertEquals("sally", g.getName());
            List<Plant> plants = g.showGarden();
            assertEquals(2, g.showGarden().size());
            checkPlant("Tomato [0]", PlantType.FRUIT, "Tomatoes require plenty " +
                    "of direct sunlight (at least 6-8 hours) daily.", plants.get(0));
            checkPlant("Parsley [1]", PlantType.HERB, "Parsley can grow well in full-sun " +
                    "or part-sun environments, but direct sunlight will optimize growth.", plants.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
