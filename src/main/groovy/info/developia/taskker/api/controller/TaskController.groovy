package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.model.Task
import info.developia.taskker.api.service.TaskService
import spark.Request
import spark.Response

import static info.developia.taskker.api.service.TaskService.isValid
import static info.developia.taskker.api.service.TaskService.isValidNew
import static info.developia.taskker.api.util.Common.isNullOrBlank

class TaskController implements Controller {
    private TaskService taskService = new TaskService()

    String getAll(Response res) {
        return buildResponse(res, 200, taskService.getGetAll())
    }

    String getByTid(Request req, Response res) {
        String tid = req.params('tid')
        if (isNullOrBlank(tid))
            throw new BadRequestException('Provided tid is not valid')
        return buildResponse(res, 200, taskService.getByTid(tid))
    }

    String create(Request req, Response res) {
        Task newTask = getTask(req.body())
        if (!isValidNew(newTask))
            throw new BadRequestException('Task does not contains minimal data to be created')
        taskService.create(newTask)
        return buildResponse(res, 201)
    }

    String update(Request req, Response res) {
        Task task = getTask(req.body())
        if (!isValid(task))
            throw new BadRequestException('Task does not contains minimal data to be created')
        taskService.update(task)
        return buildResponse(res, 200)
    }

    String markDoneAs(Request req, Response res){
        String tid = req.params('tid')
        if (isNullOrBlank(tid))
            throw new BadRequestException('Provided tid is not valid')
        taskService.markDoneAs(tid)
        return buildResponse(res, 204)
    }
}
