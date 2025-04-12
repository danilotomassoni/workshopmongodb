package com.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshopmongodb.domain.Post;
import com.workshopmongodb.domain.User;
import com.workshopmongodb.dto.AuthorDTO;
import com.workshopmongodb.dto.CommentDTO;
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

        userRepository.saveAll(Arrays.asList(maria,joao,josiani));

        Post post1 = new Post(null,dateFormat.parse("20/03/2024"),"Partiu viagem","Vou viajar para São Paulo!",new AuthorDTO(maria));
        Post post2 = new Post(null,dateFormat.parse("20/02/2025"),"Partiu viagem","Vou viajar para Fortaleza!",new AuthorDTO(maria));
        Post post3 = new Post(null,dateFormat.parse("20/01/2025"),"Partiu viagem","Vou viajar para Portugal!",new AuthorDTO(joao));
    
        CommentDTO c1 = new CommentDTO("Boa Viagem!",dateFormat.parse("20/03/2024"),new AuthorDTO(joao));
        CommentDTO c2 = new CommentDTO("Boa Viagem!",dateFormat.parse("21/03/2024"),new AuthorDTO(josiani));

        post1.getComments().addAll(Arrays.asList(c1,c2));

        postRepository.saveAll(Arrays.asList(post1,post2,post3));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        joao.getPosts().add(post3);

        userRepository.saveAll(Arrays.asList(maria,joao));
    }

}
