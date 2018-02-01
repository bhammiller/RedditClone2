package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RedditPostRepository extends CrudRepository<RedditPosts, Long>{
    List<RedditPosts> findAllByUserIgnoreCase(String ln);
}
