package org.academiadecodigo.bootcamp.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Proclaimers.<br>" +
                "To interact with this API just use api/table. GET will retrieve data and POST will add.<br>" +
                "To Delete or alter you will need customer id, then api/table/{id}. PUT will alter and DELETE will...<br>" +
                "For example HTTP.GET /api/customer will return all customer on database";
    }
}
