package developia.info.taskker.service

import developia.info.taskker.exception.NotFoundException
import developia.info.taskker.model.Task

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
