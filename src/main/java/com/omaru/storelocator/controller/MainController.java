package com.omaru.storelocator.controller;

import com.omaru.storelocator.resource.MainNavigationResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping( path = {"","/"})
    public ResponseEntity<ResourceSupport> index(){
        return new ResponseEntity<>(new MainNavigationResource(), HttpStatus.OK);
    }
}