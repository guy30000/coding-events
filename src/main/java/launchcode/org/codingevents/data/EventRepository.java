package launchcode.org.codingevents.data;

import launchcode.org.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//https://www.youtube.com/watch?v=YAISqYMOIAw 4.2 5:45
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
