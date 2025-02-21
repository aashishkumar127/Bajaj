package com.bajaj.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import java.util.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/bfhl")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/bfhl")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processData(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();

        if (!payload.containsKey("data") || !(payload.get("data") instanceof List)) {
            response.put("is_success", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<String> data = (List<String>) payload.get("data");
        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
            }
        }

        String highestAlphabet = alphabets.stream()
                                          .max(String.CASE_INSENSITIVE_ORDER)
                                          .orElse("");

        response.put("is_success", true);
        response.put("user_id", "aashish_kumar_01012002"); // Replace with your details
        response.put("email", "aashish@example.com"); // Replace with your college email
        response.put("roll_number", "CSE12345"); // Replace with your roll number
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_alphabet", highestAlphabet.isEmpty() ? new ArrayList<>() : Collections.singletonList(highestAlphabet));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
