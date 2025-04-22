package com.example.Registerform.ControllerClass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Registerform.model.User;
import com.example.Registerform.service.UserService;
@RestController
@RequestMapping("/api")  
@CrossOrigin(origins = "*") 
public class UserController {
    @Autowired
    private UserService userService;
    //store users inputs in database
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) 
    {
        userService.saveUser(user); 
        return ResponseEntity.ok("Registered successfully");	  		
    }
    // Get all users 
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
        //update users 
           @PutMapping("users/{id}")
    		public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        	   User existingUser =  userService.findById(id).orElse(null);
    		    if (existingUser==null) {
    		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    		    } 
    		    existingUser.setName(user.getName());
    		    existingUser.setAge(user.getAge());
    		    existingUser.setAddress(user.getAddress());
    		    existingUser.setEmail(user.getEmail());
    		    existingUser.setCourse(user.getCourse());
    		  //save database
    		    UserService.save(existingUser);
    		    return ResponseEntity.ok(existingUser);
    		}
    	
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) 
    {
        boolean isDeleted = userService.deleteUserById(id); // Implement this in your service layer
        if (isDeleted) 
        {
           return ResponseEntity.ok("User deleted successfully.");
        }
        else 
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
       }
    }


    

   
 
	

