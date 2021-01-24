package info.developia.taskker.api.controller

import com.google.gson.Gson
import spark.ResponseTransformer

class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson()

    @Override
    String render(Object model) throws Exception {
        return gson.toJson(model)
    }
}
