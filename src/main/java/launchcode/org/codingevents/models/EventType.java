package launchcode.org.codingevents.models;

//https://www.youtube.com/watch?v=FOvBYJxGPTQ 3.4
//https://www.youtube.com/watch?v=aFr_E2T7zZ8 5.2 Diconnected this class. Now doing nothing
public enum EventType {

    CONFERENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
