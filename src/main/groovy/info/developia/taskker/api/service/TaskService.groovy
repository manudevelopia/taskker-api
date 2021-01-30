package info.developia.taskker.api.service


import info.developia.taskker.api.model.Task
import info.developia.taskker.api.repository.TaskRepository

class TaskService {
    private final TaskRepository taskRepository = new TaskRepository()

    List<Task> getGetAll() {
        return taskRepository.getAll()
    }

    Task getById(Long id) {
        return taskRepository.getById(id)
    }
}