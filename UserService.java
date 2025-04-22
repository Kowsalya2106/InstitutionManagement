package com.example.Registerform.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Registerform.Repository.UserRepository;
import com.example.Registerform.model.User;

@Service
public class UserService {
	@Autowired
    private   UserRepository userRepository;
	
    //save user details in database
	public void saveUser(User user) 
	{
	     userRepository.save(user);  
	}
	// Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
    
	public boolean updateUserById(Long id, User user) {
		Optional<User> User = userRepository.findById(id);
		if(User.isPresent()) 
		{
			 userRepository.findById(id);
			return true;
		}
		
		return false;
	}
	public static void save(User updatedUser1) {
		// TODO Auto-generated method stub
		
	}

	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

	 


	

