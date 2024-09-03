package com.example.mulino.controller;

import com.example.mulino.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping(value = "/api/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class SubscribeController {
    @Autowired
    EmailService emailService;


    @GetMapping("/{email}")
    public ResponseEntity subscribe(@PathVariable String email) throws MessagingException {
       emailService.sendSubscriptionMail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
