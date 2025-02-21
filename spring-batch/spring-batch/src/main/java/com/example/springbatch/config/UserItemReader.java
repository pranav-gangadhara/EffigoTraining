package com.example.springbatch.config;

import com.example.springbatch.model.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserItemReader extends FlatFileItemReader<User> {

    public UserItemReader() {
        setResource(new ClassPathResource("users.csv")); // Corrected file path
        setLinesToSkip(1); // Skip header row if present
        setEncoding("UTF-8"); // Ensure proper encoding
        setStrict(false); // Avoid failure on bad lines

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

        // Define tokenizer
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("firstName", "lastName", "email");

        // Define field set mapper
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        // Set tokenizer and field set mapper to line mapper
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(lineMapper);
    }
}
