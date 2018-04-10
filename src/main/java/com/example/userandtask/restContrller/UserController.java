package com.example.userandtask.restContrller;


import com.example.userandtask.model.User;
import com.example.userandtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity users(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable(name = "id") int id) {
        User one = userRepository.findOne(id);
        if (one == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with " + id + " id no found");
        }
        return ResponseEntity.ok(one);
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") int id) {
        try {
            userRepository.delete(id);
            return ResponseEntity.ok("Delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("user with " + id + "does not exist");
        }
    }

    @PutMapping()
    public ResponseEntity updateUser(@RequestBody User user) {
        if (userRepository.exists(user.getId())) {
            userRepository.save(user);
            return ResponseEntity.ok("update");
        }
        return ResponseEntity.badRequest().body("User with" + user.getEmail() + "does not exist");
    }
}
