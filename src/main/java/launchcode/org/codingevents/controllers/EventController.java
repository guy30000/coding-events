package launchcode.org.codingevents.controllers;

//import launchcode.org.codingevents.data.EventData;
import launchcode.org.codingevents.data.EventRepository;
import launchcode.org.codingevents.models.Event;
import launchcode.org.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding
//https://www.youtube.com/watch?v=omSQO6721cU 3.2 // more on valadation (Controllers

@Controller
@RequestMapping("events")
public class EventController {
//https://www.youtube.com/watch?v=0eug2HI7rbo 4.3  //connecting controller to repo
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
//        model.addAttribute("events", EventData.getAll());
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    //lives at /events/create https://www.youtube.com/watch?v=lgT962si4eQ
    //https://www.youtube.com/watch?v=yc-bSDSDuKg //3.3 passing errors to view
    @GetMapping("create")
    public String displayCreatEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types", EventType.values());  //created early and corrected 8:40  vid3.4
        return "events/create";
    }

    //lives at /events/create https://www.youtube.com/watch?v=LnpJcq33uoM
    //https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding
    //https://www.youtube.com/watch?v=omSQO6721cU 3.2 // more on valadation (Controllers
    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            //model.addAttribute("errorMsg", "Bad data!"); //removed in 3.3
            return "events/create";
        }
//        EventData.add(newEvent);
        eventRepository.save(newEvent);
        return "redirect:";  //redirects to displayAllEvents controller
    }

    //https://www.youtube.com/watch?v=orsBBbDaJMM&t=3s 2.5
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
//        model.addAttribute("events", EventData.getAll());
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }
    //10:14
    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required=false)  int[] eventIds){
        if (eventIds!=null) {
            for (int id : eventIds) {
//                EventData.remove(id);
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";

    }


}
