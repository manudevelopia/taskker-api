package info.developia.taskker.api.service

import info.developia.taskker.api.model.Task

class TaskRepository {
    private final List<Task> tasks = [
            new Task(title: 'Task 1', description: 'Description 1'),
            new Task(title: 'Task 2', description: 'Description 2'),
            new Task(title: 'Task 3', description: 'Description 3'),
            new Task(title: 'Task 4', description: 'Description 4')
    ]

    List<Task> getGetAll() {
        return tasks
    }

    Optional<Task> getById(Long id){
        return Optional.ofNullable(tasks[id])
    }
}
