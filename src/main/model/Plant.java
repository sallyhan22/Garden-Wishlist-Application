package model;

// represents a plant

import org.json.JSONObject;
import persistence.Writeable;

// represents a plant in a garden
// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Plant implements Writeable {

    private String name;
    private PlantType type;
    private String info;

    // constructs a plant with a name, the type of plant, and info about the plant
    public Plant(String name, PlantType type, String info) {
        this.name = name;
        this.type = type;
        this.info = info;
    }

    // getters
    public String getName() {
        return name;  // stub
    }

    public PlantType getType() {
        return type;  // stub
    }

    public String getInfo() {
        return info;  // stub
    }

    @Override
    // EFFECT: returns a plant as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("info", info);
        return json;
    }


}
