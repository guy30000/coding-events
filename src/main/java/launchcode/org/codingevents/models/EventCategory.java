package launchcode.org.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class EventCategory {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

//    @ManyToOne
//    private EventCategory type;

    //public EventCategory(@Size(min=3, message="Name must be at least 3 characters long")String name) {
    // https://www.youtube.com/watch?v=GKOCCjn86yk 5.1 appears like above in video (cuts after "character" so guessed rest) but doesnt work
    public EventCategory(String name) {
                this.name = name;
    }

    public EventCategory(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCategory that = (EventCategory) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
