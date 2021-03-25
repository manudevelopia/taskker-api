package info.developia.taskker.api.service

import info.developia.taskker.api.exception.NotFoundException
import info.developia.taskker.api.model.Task
import info.developia.taskker.api.repository.TaskRepository

class TaskService {
    private final TaskRepository taskRepository = new TaskRepository()

    List<Task> getGetAll() {
        return taskRepository.getAll()
    }

    Task getById(Long id) {
        return taskRepository.getById(id)
                .orElseThrow({ -> new NotFoundException("No Task can be found with id $id") })
    }

    void create(Task task) {
        task.setTid(UUID.randomUUID().toString().replaceAll('-', ''))
        taskRepository.create(task)
    }

    void update(Task task) {
        taskRepository.update(task)
    }

    static boolean isValidNew(Task task) {
        return isNullOrBlank(task.getTid()) &&
                !isNullOrBlank(task.getTitle())
    }

    static boolean isValid(Task task) {
        return !isNullOrBlank(task.getTid()) &&
                !isNullOrBlank(task.getTitle())
    }

    private static boolean isNullOrBlank(String value) {
        value == null || value.isBlank()
    }
}
