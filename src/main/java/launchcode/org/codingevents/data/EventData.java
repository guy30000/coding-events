package launchcode.org.codingevents.data;

import launchcode.org.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=8AQtYZ_q57M //2.4 //11:20 refactor controller
public class EventData {

    private static final Map<Integer, Event> events = new HashMap<>();

    public static Collection<Event> getAll(){
        return events.values();
    }

    public static Event getById(int id){
        return events.get(id);
    }

    public static void add(Event event){
        events.put(event.getId(), event);
    }

    public static void remove(int id){
        events.remove(id);
    }

}
