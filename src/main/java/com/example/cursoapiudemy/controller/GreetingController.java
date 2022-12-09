package com.example.cursoapiudemy.controller;


import com.example.cursoapiudemy.exception.ResourceNotFoundException;
import com.example.cursoapiudemy.model.Greeting;
import com.example.cursoapiudemy.util.NumberConverter;
import com.example.cursoapiudemy.util.NumberValidation;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberValidation.isNumeric(numberOne) || !NumberValidation.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
    }

    @GetMapping(value = "/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberValidation.isNumeric(numberOne) || !NumberValidation.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);
    }

    @GetMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberValidation.isNumeric(numberOne) || !NumberValidation.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);
    }

    @GetMapping(value = "/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberValidation.isNumeric(numberOne) || !NumberValidation.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);
    }

    @GetMapping(value = "/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!NumberValidation.isNumeric(numberOne) || !NumberValidation.isNumeric(numberTwo)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return (NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo)) / 2;
    }

    @GetMapping(value = "/squareRoot/{numberOne}")
    public Double squareRoot(@PathVariable(value = "numberOne") String numberOne) throws Exception {

        if (!NumberValidation.isNumeric(numberOne)) {
            throw new ResourceNotFoundException("Please set a numeric value");
        }

        return Math.sqrt(NumberConverter.convertToDouble(numberOne));
    }
}