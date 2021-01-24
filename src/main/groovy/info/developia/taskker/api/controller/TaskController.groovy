package info.developia.taskker.api.controller

import info.developia.taskker.api.model.Task
import info.developia.taskker.api.service.TaskService
import spark.Request

class TaskController {
    private TaskService taskService = new TaskService()

    List<Task> getGetAll() {
        return taskService.getGetAll()
    }

    Task getById(Request req) {
        String id = req.params('id')
        return taskService.getById(Long.valueOf(id))
    }
}
