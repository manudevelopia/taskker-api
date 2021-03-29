package info.developia.taskker.api.controller

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.model.Task
import spark.Response

trait Controller {
    static Gson gson = new Gson()

    static String buildResponse(Response res, int status) {
        res.status(status)
        res.type('application/json')
        return ''
    }

    static String buildResponse(Response res, int status, Object obj) {
        res.status(status)
        res.type('application/json')
        return gson.toJson(obj)
    }

    static void buildResponseError(Response res, ExceptionController.ErrorResponse errorResponse) {
        res.status(errorResponse.getStatus())
        res.type('application/json')
        res.body(gson.toJson(errorResponse))
    }

    static Task getTask(String json) {
        try {
            return gson.fromJson(json, Task)
        } catch (JsonSyntaxException ignored) {
            throw new BadRequestException('Request does not contains a valid task json')
        }
    }
}
