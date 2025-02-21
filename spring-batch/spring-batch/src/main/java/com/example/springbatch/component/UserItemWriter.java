package com.example.springbatch.component;

import com.example.springbatch.model.User;
import com.example.springbatch.repository.UserRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserItemWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void write(Chunk<? extends User> chunk) throws Exception {
        userRepository.saveAll(chunk);
    }
}

