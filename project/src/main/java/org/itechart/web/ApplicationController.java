package org.itechart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {

    @RequestMapping(value = "/")
    public String baseTemplate(Model model) {
        return "forward:/views/index.html";
    }

}
