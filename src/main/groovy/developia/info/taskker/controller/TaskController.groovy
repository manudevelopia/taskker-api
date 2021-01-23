package developia.info.taskker.controller

import developia.info.taskker.model.Task
import developia.info.taskker.service.TaskService
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
