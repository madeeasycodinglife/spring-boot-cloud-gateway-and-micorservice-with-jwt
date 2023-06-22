package com.madeeasy.service;


import com.madeeasy.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Service
@RequiredArgsConstructor
public class UserService {
    private final List<User> list = new ArrayList<User>();

    public boolean validateUserName(String name) {
        Stream<User> userStream = list.stream()
                .filter(user -> user.getUsername().equals(name));
        Optional<User> first = userStream.findFirst();
        User user = first.get();
        return user.getUsername().equals(name);
    }

    public boolean saveUser(String name, String password) {
        return list.add(new User(name, password));
    }
}