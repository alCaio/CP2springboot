package br.com.fiap.apitask.service;

import br.com.fiap.apitask.dto.UpdateTaskDto;
import br.com.fiap.apitask.model.Task;
import br.com.fiap.apitask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void registration(Task task) {
        repository.save(task);
    }

    public Page<Task> listAll(Pageable pageable) {
        return repository.findAllByIsActiveTrue(pageable);
    }

    public Task update(UpdateTaskDto data) {
        Optional<Task> optionalTask = repository.findById(data.id());

        if (optionalTask.isPresent()) {
            Task updated = optionalTask.get();
            updateTaskFields(updated, data);
            repository.save(updated);
            return updated;
        }

        return null;
    }

    public void delete(Long id) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            Task deleted = optionalTask.get();
            deleted.setIsActive(false);
            repository.save(deleted);
        }
    }

    private void updateTaskFields(Task task, UpdateTaskDto data) {
        if (data.title() != null) {
            task.setTitle(data.title());
        }
        if (data.description() != null) {
            task.setDescription(data.description());
        }
        if (data.status() != null) {
            task.setStatus(data.status());
        }
        if (data.dueDate() != null) {
            task.setDueDate(data.dueDate());
        }
    }
}