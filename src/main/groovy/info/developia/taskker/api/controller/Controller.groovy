package info.developia.taskker.api.controller

import com.google.gson.Gson
import spark.Response

trait Controller {
    static Gson gson = new Gson()

    static String buildResponse(Response res, int status){
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
}
