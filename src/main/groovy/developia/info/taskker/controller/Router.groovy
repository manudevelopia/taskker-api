package developia.info.taskker.controller

import developia.info.taskker.exception.NotFoundException

import static spark.Spark.*

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

        exception(NotFoundException, { exception, req, res -> res.status(404) })
    }
}
