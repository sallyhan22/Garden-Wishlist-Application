package persistence;


import model.Garden;
import model.Plant;
import model.PlantType;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads garden from JSON data stored in file
// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


public class JsonReader {


    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads garden from file and returns it;
// throws IOException if an error occurs reading data from file
    public Garden read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGarden(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();


        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }


        return contentBuilder.toString();
    }


    // EFFECTS: parses garden from JSON object and returns it
    private Garden parseGarden(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Garden g = new Garden(name);
        addPlants(g, jsonObject);
        return g;
    }


    // MODIFIES: g
// EFFECTS: parses plants from JSON object and adds them to garden
    private void addPlants(Garden g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plants");
        for (Object json : jsonArray) {
            JSONObject nextPlant = (JSONObject) json;
            addThingy(g, nextPlant);
        }
    }


    // MODIFIES: g
// EFFECTS: parses plant from JSON object and adds it to garden
    private void addThingy(Garden g, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PlantType type = PlantType.valueOf(jsonObject.getString("type"));
        String info = jsonObject.getString("info");
        Plant p = new Plant(name, type, info);
        g.addPlant(p);
    }
}
