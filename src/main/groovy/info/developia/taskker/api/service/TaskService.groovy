package info.developia.taskker.api.service

import info.developia.taskker.api.exception.NotFoundException
import info.developia.taskker.api.model.Task

class TaskService {
    private final TaskRepository taskRepository = new TaskRepository()

    List<Task> getGetAll() {
        return taskRepository.getGetAll()
    }

    Task getById(Long id) {
        return taskRepository.getById(id)
                .orElseThrow({ -> new NotFoundException() })
    }
}
