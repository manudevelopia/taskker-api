package info.developia.taskker.api.controller

import static spark.Spark.get
import static spark.Spark.path

class Router {

    static void init() {
        JsonTransformer transformer = new JsonTransformer()
        TaskController taskController = new TaskController()

        path("/api", { ->
            path("/tasks", { ->
                get("/all", { req, res -> taskController.getAll }, transformer)
                get("/:id", { req, res -> taskController.getById(req) }, transformer)
            })
        })
    }
}
