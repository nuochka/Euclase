package com.euclase.task_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.euclase.task_service.model.Task;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{

    List<Task> findAll();
    Optional<Task> findById(String id);
    <S extends Task> S save (S task);
    void deleteById(String id);
    void deleteAll();

}
