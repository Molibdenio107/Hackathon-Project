package org.academiadecodigo.bootcamp.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public String home(){
        return "index.html";
    }
    @RequestMapping(method = RequestMethod.GET, path = {"/css"})
    public String homeCss(){
        return "assets/css/style.css";
    }
}
