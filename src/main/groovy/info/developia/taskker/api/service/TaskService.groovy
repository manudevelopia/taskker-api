package info.developia.taskker.api.service

import info.developia.taskker.api.exception.NotFoundException
import info.developia.taskker.api.model.Task
import info.developia.taskker.api.repository.TaskRepository

import static info.developia.taskker.api.util.Common.isNullOrBlank

class TaskService {
    private final TaskRepository taskRepository = new TaskRepository()

    List<Task> getGetAll() {
        return taskRepository.getAll()
    }

    Task getByTid(String tid) {
        return taskRepository.getById(tid)
                .orElseThrow({ -> new NotFoundException("No Task can be found with tid $tid") })
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

    void markDoneAs(String tid) {
        Task task = getByTid(tid)
        task.done = !task.done
        taskRepository.markDoneAs(task)
    }
}
