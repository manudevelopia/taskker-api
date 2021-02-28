package info.developia.taskker.api.controller

import com.google.gson.JsonSyntaxException
import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.model.Task
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

    String create(Request req, Response res) {
        Task newTask
        try {
            newTask = gson.fromJson(req.body(), Task)
        } catch (JsonSyntaxException e){
            throw new BadRequestException('Request does not contains a valid task json')
        }
        taskService.create(newTask)
        return buildResponse(res, 201)
    }
}
