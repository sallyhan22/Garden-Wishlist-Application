package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// represents a log of garden events
// code influenced by https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class EventLog implements Iterable<Event> {
    private static EventLog gardenLog;
    private Collection<Event> events;

    // EFFECTS: creates an event log with no events
    private EventLog() {
        events = new ArrayList<Event>();
    }


    // EFFECTS: return the instance of EventLog, create it if it doesn't already exist
    public static EventLog getInstance() {
        if (gardenLog == null) {
            gardenLog = new EventLog();
        }

        return gardenLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }


    // MODIFIES: this
    // EFFECTS: clears event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }


}
