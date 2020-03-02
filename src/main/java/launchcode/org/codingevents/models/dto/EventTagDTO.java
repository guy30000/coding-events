package launchcode.org.codingevents.models.dto;

import launchcode.org.codingevents.models.Event;
import launchcode.org.codingevents.models.Tag;
import javax.validation.constraints.NotNull;

//https://www.youtube.com/watch?v=1qMaEv_CJ6k 6.4 m2n and Data Transfer Objects
public class EventTagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO(){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
