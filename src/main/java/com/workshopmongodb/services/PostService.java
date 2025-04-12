package com.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongodb.domain.Post;
import com.workshopmongodb.repositories.PostRepository;
import com.workshopmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        if(post.isEmpty()){
            throw  new ObjectNotFoundException("Object not found.");
        }
        return post.get();
    }
    public List<Post> findByTitle(String text){
        return repository.findByTitleContainingIgnoreCase(text);
    }

}
