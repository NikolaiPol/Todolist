package com.todo.todolist.service.user;

import com.todo.todolist.DTO.RegisterRequest;
import com.todo.todolist.entity.User;
import com.todo.todolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    @Autowired
    public RegService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }


    public void register(RegisterRequest registerRequest){
        User user = convertToUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private User convertToUser(RegisterRequest user) {
        return mapper.map(user,User.class);
    }


}
