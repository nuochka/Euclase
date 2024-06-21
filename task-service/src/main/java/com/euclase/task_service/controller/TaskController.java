package com.euclase.task_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.euclase.task_service.repository.TaskRepository;
import com.euclase.task_service.service.TaskService;
import com.euclase.task_service.model.Task;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        if (!model.containsAttribute("newTask")) {
            model.addAttribute("newTask", new Task());
        }
        
        return "tasks";
    }

    @PostMapping("/add")
    public String createTask(@ModelAttribute("newTask") Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
