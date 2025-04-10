package com.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshopmongodb.domain.Post;
import com.workshopmongodb.domain.User;
import com.workshopmongodb.repositories.PostRepository;
import com.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null,"Maria","cecilia@gmail.com");
        User joao = new User(null,"João","joao@gmail.com");
        User josiani = new User(null,"Josiani","josiani@gmail.com");

        Post post1 = new Post(null,dateFormat.parse("20/03/2024"),"Partiu viagem","Vou viajar para São Paulo!",maria);
        Post post2 = new Post(null,dateFormat.parse("20/02/2025"),"Partiu viagem","Vou viajar para Fortaleza!",maria);
        Post post3 = new Post(null,dateFormat.parse("20/01/2025"),"Partiu viagem","Vou viajar para Portugal!",joao);
    
        userRepository.saveAll(Arrays.asList(maria,joao,josiani));
        postRepository.saveAll(Arrays.asList(post1,post2,post3));
    }

}
