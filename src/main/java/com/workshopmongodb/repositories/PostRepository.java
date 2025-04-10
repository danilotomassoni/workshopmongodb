package com.workshopmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.workshopmongodb.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
