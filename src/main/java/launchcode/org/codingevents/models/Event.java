package launchcode.org.codingevents.models;

import java.util.Objects;

//https://www.youtube.com/watch?v=tDfwNJ3Nk_M //p2.1 crated model, updated view and class for adding
//https://www.youtube.com/watch?v=5dtyojtADbk //2.2 added  disc feild
//https://www.youtube.com/watch?v=ijnIgreiNHU&t=7s //2.3added ID & next ID and hash
//https://www.youtube.com/watch?v=8AQtYZ_q57M //2.4
public class Event {

    private int id;
    private static int nextId=1;
    private String name;
    public String discription;

    public Event(String name, String discription) {
        this.name = name;
        this.discription = discription;
        this.id=nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getId() {
        return id;
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
