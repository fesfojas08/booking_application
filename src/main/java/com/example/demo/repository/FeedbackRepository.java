package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

}
