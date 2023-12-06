package com.example.diplom33.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
//    private final ClientRepository clientRepository;

//    public List<Client> getAllClient(){
//        return clientRepository.findAll();
//    }

//    public Optional<Client> getClient(int id){
//        return clientRepository.findById(id);
//    }
//
//    @Transactional
//    public void  update(Client client, int id){
//        client.setId(id);
//        clientRepository.save(client);
//    }
}
