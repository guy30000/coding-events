package launchcode.org.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
//https://www.youtube.com/watch?v=bLK-VtZgx0Q copied from vid 6.2 2:57

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 1,max=25)
    @NotBlank
    private String name;

    //https://www.youtube.com/watch?v=qtbkUXAjpt4&t=110s 6.3 3:30
    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();


    public Tag(String name) {
        this.name = name;
    }

    public Tag() {   }

    public String getName() {return name;}

    public String getDisplayName() {return "#" + name + " ";}

    public void setName(String name) {this.name = name;}

    public List<Event> getEvents() {
        return events;
    }
}
