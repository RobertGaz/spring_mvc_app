package orlanda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import orlanda.dao.person.PersonDao;
import orlanda.dao.person.PersonDaoJdbcTemplate;

@Controller
@RequestMapping("/batch_update")
public class BatchController {

    @Autowired
    PersonDaoJdbcTemplate personDao;

    @GetMapping
    public String index() {
        return "batch/index";
    }

    @GetMapping("usual_update")
    public String usualUpdate(Model model) {
        long result = personDao.multipleUpdate();
        model.addAttribute("updateType", "Usual update");
        model.addAttribute("result", result);

        return "batch/result";
    }

    @GetMapping("batch_update")
    public String batchUpdate(Model model) {
        long result = personDao.batchUpdate();
        model.addAttribute("updateType", "Batch update");
        model.addAttribute("result", result);
        return "batch/result";
    }

}
