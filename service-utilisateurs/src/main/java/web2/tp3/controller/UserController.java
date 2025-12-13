package web2.tp3.controller;

import web2.tp3.model.User;
import web2.tp3.model.UserDTO;
import web2.tp3.model.UserRole;
import web2.tp3.service.UserService;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Builder
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RequestMapping("api/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/email/{email}")
    User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/roles/{role}")
    List<User> getUserByRole(@PathVariable UserRole role){
        return userService.getUsersByRole(role);
    }

    @GetMapping("/count/{role}")
    Long countByRole(@PathVariable UserRole role){
        return userService.countByRole(role);
    }

    @GetMapping("/{id}/role")
    public UserRole getUserRole(@PathVariable Long id) {
        return userService.getUserById(id).getRole();
    }

    @GetMapping("/deleteNews/{newsId}")
    Boolean canDeleteNews(@PathVariable Long newsId){
        return userService.canUserDeleteNews(newsId);
    }

    @GetMapping("/modify/{newsId}")
    Boolean canModifyNews(@PathVariable Long newsId){
        return userService.canUserModifyNews(newsId);
    }

    @PostMapping("/post")
    User createUser(@RequestBody @Valid UserDTO userDTO){
        return userService.createOneUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    void updateUser(@RequestBody UserDTO userDTO,  @PathVariable Long id){
        userService.updateUser(id, userDTO);
    }
}
