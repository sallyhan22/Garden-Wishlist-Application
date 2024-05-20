package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// represents a garden containing plants
// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class Garden implements Writeable {
    private List<Plant> plantsInGarden = new ArrayList<>();
    private List<String> plantNames = new ArrayList<>();
    private String name;

    // creates a list of all plants that have been added to the garden, starts with an empty list of plants
    public Garden(String name) {
        this.name = name;
    }


    // getters
    public List<Plant> showGarden() {
        return plantsInGarden;
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds p to list of plants in garden
    public void addPlant(Plant p) {
        plantsInGarden.add(p);
        EventLog.getInstance().logEvent(new Event(p.getName() + " was added to " + name));
    }

    // MODIFIES: this
    // EFFECTS: removes the plant with the given name from garden, removes the first instance if there are duplicates
    public void removePlant(String name) {
        boolean keepGoing = true;

        int next = 0;

        while (keepGoing) {
            if (next < plantsInGarden.size()) {
                if (Objects.equals(plantsInGarden.get(next).getName(), name)) {
                    plantsInGarden.remove(plantsInGarden.get(next));
                    EventLog.getInstance().logEvent(new Event(name + " was removed from " + getName()));
                    keepGoing = false;
                } else {
                    next++;
                }
            } else {
                keepGoing = false;
            }
        }
    }

    // EFFECTS: produces the information of the plant with the given name in garden
    public String findPlantInfo(String name) {
        String info = null;
        for (Plant plant: plantsInGarden) {
            if (Objects.equals(plant.getName(), name)) {
                info = plant.getInfo();
            }
        }
        return info;
    }

    // EFFECTS: counts the number of plants that are the same as the given plant type
    public int countNumPlantTypes(PlantType pt) {
        int count = 0;
        for (Plant p: plantsInGarden) {
            if ((p.getType()) == pt) {
                count++;
            }
        }
        return count;
    }

    //EFFECTS: produces a list of all plant names in garden
    public List<String> showPlantNames() {
        for (Plant p: plantsInGarden) {
            this.plantNames.add(p.getName());
        }
        return plantNames;
    }

    @Override
    // EFFECT: returns garden as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("plants", plantsToJson());
        return json;
    }


    // EFFECTS: returns plants in this garden as a JSON array
    private JSONArray plantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Plant p : plantsInGarden) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }


}
