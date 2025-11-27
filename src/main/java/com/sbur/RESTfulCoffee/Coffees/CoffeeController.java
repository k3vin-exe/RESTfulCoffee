package com.sbur.RESTfulCoffee.Coffees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private List<CoffeeModel> coffees = new ArrayList<>();

    public CoffeeController() {
        coffees.addAll(List.of(
                new CoffeeModel("Café Cereza"),
                new CoffeeModel("Café Ganador"),
                new CoffeeModel("Café Lareño"),
                new CoffeeModel("Café Três Pontas")
        ));
    }

//    Returns the list of coffees
    @GetMapping
    Iterable<CoffeeModel> getCoffees() {
        return coffees;
    }

    @PostMapping
    CoffeeModel postCoffee(@RequestBody CoffeeModel coffee) {
        if(coffee.getId() == null) {
            coffee.setId(UUID.randomUUID().toString());
        }
        coffees.add(coffee);
        return coffee;
    }

//    Returns the coffee with the id of the path variable (if it exists)
    @GetMapping("/{id}")
    Optional<CoffeeModel> getCoffeesById(@PathVariable String id) {
        for (CoffeeModel c : coffees) {

            if (c.getId().equals(id)) {
                return Optional.of(c);
            }

        }

        return Optional.empty();

    }

    @PutMapping("/{id}")
    ResponseEntity<CoffeeModel> putCoffee(@PathVariable String id, @RequestBody CoffeeModel coffee) {

        for (CoffeeModel c : coffees) {
            if(c.getId().equals(id)) {
                c.setName(coffee.getName());
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
        }

        coffee.setId(id);
        coffees.add(coffee);
        return new ResponseEntity<>(coffee, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }



}