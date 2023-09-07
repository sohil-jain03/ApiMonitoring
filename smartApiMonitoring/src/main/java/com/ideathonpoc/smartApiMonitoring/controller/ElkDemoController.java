package com.ideathonpoc.smartApiMonitoring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideathonpoc.smartApiMonitoring.model.User;

@RestController
public class ElkDemoController {
private static final Logger logger = LogManager.getLogger(ElkDemoController.class);

private List<User> userDetails =new ArrayList<>();

@GetMapping(path = "/welcome")
    public ResponseEntity<String> welcome() {
	long starttime=System.currentTimeMillis();
        logger.debug("Welcome to ELK demo service");
        
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        long endtime=System.currentTimeMillis();
       long apiresponsetime=(endtime-starttime)/1000;
        logger.debug("Api Respond in "+apiresponsetime+"s");
        
        return ResponseEntity.ok("Hello ELK Integration!!!");
    	
    }
@GetMapping(path = "/users/{name}")
    public ResponseEntity<String> getUserByName(@PathVariable String name) {
        if (name.equals("ADMIN")) {
            logger.debug("Access by ADMIN triggered");
            return ResponseEntity.ok("Access Granted to " + name);
        } else {
            logger.error("Access denied for: " + name);
            return new ResponseEntity<>(("Access Denied for " + name), HttpStatus.BAD_REQUEST);
        }
    }
@PostMapping(path = "/users/")
public ResponseEntity<String> addUser(@RequestBody User user) {

	try{
		userDetails.add(user);
		logger.debug("User is succesfuly added");
		return ResponseEntity.ok("user is added");
	}
	catch(Exception e) {
		
		logger.debug("User cannot be added");
		return new ResponseEntity<>(("User cannot be added"), HttpStatus.BAD_REQUEST);
		
	}
	
	}


}
