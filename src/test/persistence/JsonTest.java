package persistence;


import model.Plant;
import model.PlantType;


import static org.junit.jupiter.api.Assertions.assertEquals;


// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkPlant(String name, PlantType type, String info, Plant p) {
        assertEquals(name, p.getName());
        assertEquals(type, p.getType());
        assertEquals(info, p.getInfo());
    }


}
