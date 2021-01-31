package info.developia.taskker.api.controller

import com.google.gson.Gson
import spark.Response

trait Controller {
    private Gson gson = new Gson()

    String buildResponse(Response res, int status, Object obj) {
        res.status(status)
        res.type('application/json')
        return gson.toJson(obj)
    }
}
