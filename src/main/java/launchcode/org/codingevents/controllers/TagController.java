package launchcode.org.codingevents.controllers;

import launchcode.org.codingevents.data.TagRepository;
import launchcode.org.codingevents.models.Tag;
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
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model){
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayCreatTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model){
        System.out.println("test3a");
        if (errors.hasErrors()) {
            System.out.println("test3b");
            model.addAttribute("title", "Create Tag");
            model.addAttribute(tag);  //Not this. Appears to pass the failed data back to view
            return "tags/create";
        }
        System.out.println("test3c");

        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        tagRepository.save(tag);
        return "redirect:/tags";


    }


}
