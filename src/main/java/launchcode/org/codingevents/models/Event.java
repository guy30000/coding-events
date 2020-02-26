package launchcode.org.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

//https://www.youtube.com/watch?v=tDfwNJ3Nk_M //p2.1 crated model, updated view and class for adding
//https://www.youtube.com/watch?v=5dtyojtADbk //2.2 added  disc feild
//https://www.youtube.com/watch?v=ijnIgreiNHU&t=7s //2.3added ID & next ID and hash
//https://www.youtube.com/watch?v=8AQtYZ_q57M //2.4
//https://www.youtube.com/watch?v=1aZxU0-dhgw //3.1 Annotation

//https://www.youtube.com/watch?v=YAISqYMOIAw 4.2 persistants
@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="Name is required")  //cant put in white spaves
    @Size(min=3, max=50, message="Name must be between 3 and 50 characters")
    private String name;

    @Size(max=500, message="Description too long!")
    public String description;

    @NotBlank(message = " Email is Required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    //https://www.youtube.com/watch?v=FOvBYJxGPTQ 3.4 2:40
    private EventType type;

    public Event(String name, String description, String contactEmail, EventType type) {
        //this(); 4.2 removed this for database
        //3.3 12:00  Added this which calles other constructor. Reason is that the ID will default to 0.
        //Meaning that each event will overwright the first. However this means that All id's will be even.
        //The rendering of the displayCreatEventForm will create an ID, and then another will be created when saving.
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;

    }

//https://www.youtube.com/watch?v=yc-bSDSDuKg 3.3 created early, changed later
    //12:00 Cut the lins from other cunstructor
    //4.2 removed ID
    public Event(){
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    //net concept
    @Override
    public String toString() {
        return name;
    }

    // https://www.youtube.com/watch?v=ijnIgreiNHU&t=7s 2.3 5:40
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
