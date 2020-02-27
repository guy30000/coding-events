package launchcode.org.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//https://www.youtube.com/watch?v=bLK-VtZgx0Q copied from vid 6.2 2:57

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 1,max=25)
    @NotBlank
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {   }

    public String getName() {return name;}

    public String getDisplayName() {return "#" + name + " ";}

    public void setName(String name) {this.name = name;}
}
