package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.model.Task
import info.developia.taskker.api.service.TaskService
import spark.Request
import spark.Response

import static info.developia.taskker.api.service.TaskService.isValidNew

class TaskController implements Controller {
    private TaskService taskService = new TaskService()

    String getAll(Response res) {
        return buildResponse(res, 200, taskService.getGetAll())
    }

    String getById(Request req, Response res) {
        String id = req.params('id')
        if (!isPositiveLong(id))
            throw new BadRequestException('Provided id is not valid')
        return buildResponse(res, 200, taskService.getById(Long.valueOf(id)))
    }

    String create(Request req, Response res) {
        Task newTask = getTask(req.body())
        if (!isValidNew(newTask))
            throw new BadRequestException('Task does not contains minimal data to be created')
        taskService.create(newTask)
        return buildResponse(res, 201)
    }
}
