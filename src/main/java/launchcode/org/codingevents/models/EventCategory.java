package launchcode.org.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//https://www.youtube.com/watch?v=GKOCCjn86yk&t=117s changed 5.1 5:30
@Entity
public class EventCategory extends AbstractEntity{


    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    //https://www.youtube.com/watch?v=RLykFBY9Rys 5.3 One-to-Many
    @OneToMany(mappedBy = "eventCat")
    private final List<Event> events = new ArrayList<>();


    //public EventCategory(@Size(min=3, message="Name must be at least 3 characters long")String name) {
    // https://www.youtube.com/watch?v=GKOCCjn86yk 5.1 appears like above in video (cuts after "character" so guessed rest) but doesnt work
    public EventCategory(String name) {
                this.name = name;
    }

    public EventCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

}
