package orlanda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import orlanda.models.Person;
import orlanda.services.ItemsService;
import orlanda.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Autowired
    ItemsService itemsService;

    @GetMapping()
    public String index(Model model) {

        itemsService.findByOwner(peopleService.getAll().get(1));
        itemsService.findByName("AirPods");
        peopleService.test();

        model.addAttribute("people", peopleService.getAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.getById(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new_person";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new_person";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.getById(id));
        return "people/edit_person";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/edit_person";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

}
