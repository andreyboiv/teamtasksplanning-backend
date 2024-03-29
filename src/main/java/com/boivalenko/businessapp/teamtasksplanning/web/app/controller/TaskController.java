package com.boivalenko.businessapp.teamtasksplanning.web.app.controller;

import com.boivalenko.businessapp.teamtasksplanning.web.app.entity.Task;
import com.boivalenko.businessapp.teamtasksplanning.web.app.search.TaskSearchValues;
import com.boivalenko.businessapp.teamtasksplanning.web.app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Task task) {
        return this.taskService.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        return this.taskService.deleteById(id);
    }

    @PostMapping("/id")
    public ResponseEntity<Task> findById(@RequestBody Long id) {
        return this.taskService.findById(id);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Task>> findAllByEmail(@RequestBody String email) {
        return this.taskService.findAllByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Task task) {
        return this.taskService.update(task);
    }

    @PostMapping("/findAllByEmailQuery")
    public ResponseEntity<List<Task>> findAllByEmailQuery(@RequestBody TaskSearchValues taskSearchValues) {
        return this.taskService.findAllByEmailQuery(taskSearchValues.getTitle(), taskSearchValues.getEmail());
    }

    // Suche nach beliebigen Parameter
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
        return this.taskService.findByParams(taskSearchValues);
    }

}
