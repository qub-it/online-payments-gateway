package org.fenixedu.demo.ui;

import org.fenixedu.bennu.spring.portal.SpringApplication;
import org.fenixedu.bennu.spring.portal.SpringFunctionality;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/demo")
@SpringApplication(group = "logged", path = "demo", title = "title.Demo")
@SpringFunctionality(app = DemoController.class, title = "title.Demo")
public class DemoController {

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("world", "World");
        return "demo/home";
    }

}
