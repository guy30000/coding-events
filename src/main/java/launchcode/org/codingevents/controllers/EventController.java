package launchcode.org.codingevents.controllers;

//import launchcode.org.codingevents.data.EventData;
import launchcode.org.codingevents.data.EventCategoryRepository;
import launchcode.org.codingevents.data.EventRepository;
import launchcode.org.codingevents.data.TagRepository;
import launchcode.org.codingevents.models.Event;
import launchcode.org.codingevents.models.EventCategory;
import launchcode.org.codingevents.models.Tag;
import launchcode.org.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding
//https://www.youtube.com/watch?v=omSQO6721cU 3.2 // more on valadation (Controllers

@Controller
@RequestMapping("events")
public class EventController {
    //https://www.youtube.com/watch?v=0eug2HI7rbo 4.3  //connecting controller to repo
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCategoryRepository eventCatRepo;
    @Autowired // 6.4 5:45
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
//        model.addAttribute("events", EventData.getAll());
            model.addAttribute("events", eventRepository.findAll());
        } else {
            //https://www.youtube.com/watch?v=RLykFBY9Rys 5.3 8:30 //explains Optional. this showd
            Optional<EventCategory> result = eventCatRepo.findById(categoryId);
            if (!result.isPresent()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    //lives at /events/create https://www.youtube.com/watch?v=lgT962si4eQ
    //https://www.youtube.com/watch?v=yc-bSDSDuKg //3.3 passing errors to view
    @GetMapping("create")
    public String displayCreatEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCatRepo.findAll());  //created early and corrected 8:40  vid3.4
        return "events/create";
    }

    //lives at /events/create https://www.youtube.com/watch?v=LnpJcq33uoM
    //https://www.youtube.com/watch?v=gzk__EWfvaw 2.6 model binding
    //https://www.youtube.com/watch?v=omSQO6721cU 3.2 // more on valadation (Controllers
    @PostMapping("create")
    public String processCreateEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("event", newEvent);
            model.addAttribute("categories", eventCatRepo.findAll());
            return "events/create";
        }
        eventRepository.save(newEvent); //6.1 9:45 new concept cascade (in model/event)
        return "redirect:";  //redirects to displayAllEvents controller
    }

    //https://www.youtube.com/watch?v=orsBBbDaJMM&t=3s 2.5
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
//        model.addAttribute("events", EventData.getAll());
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    //10:14
    @PostMapping("delete")
    public String processDeleteEvent(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
//                EventData.remove(id);
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";

    }

    //https://www.youtube.com/watch?v=bLK-VtZgx0Q 6.2 copied controller
    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        if (!result.isPresent()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model. addAttribute("tags", event.getTags());// 6.4 2:50 added to pass tags
        }
        return "events/detail";
    }

    //https://www.youtube.com/watch?v=1qMaEv_CJ6k 6.4 3:15  //responds to /events/add-tags?eventId=13
    @GetMapping("add-tag")
    public String DisplayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        //model.addAttribute("event", event);  //6.4 21:30 this changed and next 2 lines changed
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag",eventTag);  //10:00 name changed from earlier creation //22:00 changed again
        return "events/add-tag.html";
    }

    @PostMapping("add-tag") // 6.4 14:20
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTagDTO, Model model, Errors errors){
        System.out.println("test1");
        if (!errors.hasErrors()){
            System.out.println("test2");
            Event event = eventTagDTO.getEvent();
            Tag tag = eventTagDTO.getTag();
            System.out.println("tag pre if " + tag);
            if (!event.getTags().contains(tag)){
                System.out.println("test tag");
                System.out.println("tag- "+tag);
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        System.out.println("test3");
        return "redirect:events/add-tag.html";  //"When you have a form submission you should always have a redirect" 6.4 18:45


    }


}
