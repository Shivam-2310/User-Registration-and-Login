package com.loginform.service;

import com.loginform.model.UsersModel;
import com.loginform.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email){
        if(login == null && password == null){
            return null;
        }else{
            if(usersRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate Login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String Password){
        return usersRepository.findByLoginAndPassword(login, Password).orElse(null);
    }
}
