package launchcode.org.codingevents.controllers;

import launchcode.org.codingevents.data.EventData;
import launchcode.org.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create https://www.youtube.com/watch?v=lgT962si4eQ
    @GetMapping("create")
    public String renderCreatEventForm(Model model){
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    //lives at /events/create https://www.youtube.com/watch?v=LnpJcq33uoM
    //https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding
    @PostMapping("create")
    //public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription) {
    public String createEvent(@ModelAttribute Event newEvent) {
        //EventData.add(new Event(eventName, eventDescription));
        EventData.add(newEvent);
        return "redirect:";  //redirects to displayAllEvents controller
    }

    //https://www.youtube.com/watch?v=orsBBbDaJMM&t=3s 2.5
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }
    //10:14
    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required=false)  int[] eventIds){
        if (eventIds!=null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";

    }


}
