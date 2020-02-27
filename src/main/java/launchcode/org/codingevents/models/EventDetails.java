package launchcode.org.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//https://www.youtube.com/watch?v=0yNIbAcd4ng 6.1 Created this
@Entity
public class EventDetails extends AbstractEntity{

    @Size(max=500, message="Description too long!")
    public String description;

    @NotBlank(message = " Email is Required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    // 6.1 to make ED aware of E as E is aware of ed you could add this code. Guess it would make it accessable the other way around compared to our usages
//    @OneToOne(mappedBy = "eventDetails")
//    private Event event;



    public EventDetails(@Size(max = 500, message = "Description too long!") String description, @NotBlank(message = " Email is Required") @Email(message = "Invalid email. Try again.") String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public EventDetails(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
