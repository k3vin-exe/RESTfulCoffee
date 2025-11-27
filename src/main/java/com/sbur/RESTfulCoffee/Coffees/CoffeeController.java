package com.sbur.RESTfulCoffee.Coffees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CoffeeController {
    @GetMapping("/")
    String home() {
        return "Essa é a página home";
    }

    private List<CoffeeModel> coffees = new ArrayList<>();

    public CoffeeController() {
        coffees.addAll(List.of(
                new CoffeeModel("Café Cereza"),
                new CoffeeModel("Café Ganador"),
                new CoffeeModel("Café Lareño"),
                new CoffeeModel("Café Três Pontas")
        ));
    }

    @GetMapping("/coffees")
    Iterable<CoffeeModel> getCoffees() {
        return coffees;
    }
}
