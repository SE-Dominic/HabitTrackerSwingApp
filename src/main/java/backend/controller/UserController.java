package backend.controller;

import backend.service.UserService;
import lombok.RequiredArgsConstructor;
import shared.UserDTO;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; //get, post, delete mapping, etc

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService service;

    @PostMapping()
    public UserDTO createUserPost(@RequestBody UserDTO request){
        return service.createUser(request);
    }
    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO request) {
        return service.updateUser(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
