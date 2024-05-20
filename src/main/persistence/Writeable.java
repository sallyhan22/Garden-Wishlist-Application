package persistence;

import org.json.JSONObject;

// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}


