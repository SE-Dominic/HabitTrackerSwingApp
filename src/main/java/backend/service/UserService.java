package backend.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;  // Available now

import backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import shared.UserDTO;
import shared.User;
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    //create User
    public UserDTO createUser(UserDTO request) {
        User user = new User(request.getUsername(), request.getPassword()); //craete user object
        User savedUser = repository.save(user); //save to repository, get ID back
        return toDataTransferObject(savedUser); //return dto object with added id
    }
    //get all users
    public List<UserDTO> getAllUsers() {
        List<UserDTO> dto_list = new ArrayList<>();
        
        List<User> list = repository.findAll();
        for (int i = 0; i < list.size(); i++) {
            dto_list.add(toDataTransferObject(list.get(i)));
        }
        return dto_list;
    }

    public UserDTO getUserById(Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found."));
            return toDataTransferObject(user);
        }
        
        public UserDTO updateUser(Long id, UserDTO request) {
            User user = repository.findById(id) //find user or throw exception
                .orElseThrow(() -> new RuntimeException("User not found."));
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            User updatedUser = repository.save(user); //save new updated info
            return toDataTransferObject(updatedUser);
            
        }

        public void deleteUser(Long id) {
            repository.deleteById(id);
        }

    public UserDTO toDataTransferObject(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
