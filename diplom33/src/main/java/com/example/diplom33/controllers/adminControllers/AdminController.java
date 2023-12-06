package com.example.diplom33.controllers.adminControllers;


import com.example.diplom33.dto.ClientDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import com.example.diplom33.services.ClientService;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/clients")
public class AdminController {
    private final UserService userService;
    private final ClientService clientService;


    @GetMapping
    public List<UserDTO> getAllClients() {
        return userService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO showClient(@PathVariable int id) {

        return userService.getClient(id);
    }

    @PatchMapping("/{id}")
    public void updateClient(@RequestBody User user, @PathVariable int id) {
        userService.update(user, id);
    }

}
