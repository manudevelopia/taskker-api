package info.developia.taskker.api.controller

import info.developia.taskker.api.service.TaskService
import spark.Request
import spark.Response

class TaskController implements Controller {
    private TaskService taskService = new TaskService()

    String getAll(Response res) {
        return buildResponse(res, 200, taskService.getGetAll())
    }

    String getById(Request req, Response res) {
        String id = req.params('id')
        return buildResponse(res, 200, taskService.getById(Long.valueOf(id)))
    }
}
