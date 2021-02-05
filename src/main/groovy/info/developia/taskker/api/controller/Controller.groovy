package info.developia.taskker.api.controller

import com.google.gson.Gson
import spark.Response

trait Controller {
    private static Gson gson = new Gson()

    static String buildResponse(Response res, int status, Object obj) {
        res.status(status)
        res.type('application/json')
        return gson.toJson(obj)
    }
}
