package com.example.springbatch.component;

import com.example.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) {
        user.setEmail(user.getEmail().toLowerCase());  // Normalize email
        return user;
    }
}
