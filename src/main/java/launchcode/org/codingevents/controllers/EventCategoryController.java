package launchcode.org.codingevents.controllers;


import launchcode.org.codingevents.data.EventCategoryRepository;
import launchcode.org.codingevents.data.EventRepository;
import launchcode.org.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("cat")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCatRepo;

    @GetMapping
    public String displayCat (Model model){
        model.addAttribute("title", "All Categories");
        model.addAttribute("cats", eventCatRepo.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String displayCreateCat(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateCat(@ModelAttribute @Valid EventCategory eventCat, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        model.addAttribute("title", "All Categories");
        model.addAttribute("cats", eventCatRepo.findAll());
        eventCatRepo.save(eventCat);
        return "redirect:/cat";
    }
}
