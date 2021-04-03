package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static info.developia.taskker.api.controller.ExceptionController.badRequestException
import static info.developia.taskker.api.controller.ExceptionController.notFoundException
import static spark.Spark.*

class Router {
    private final static Logger LOG = LoggerFactory.getLogger(Router)

    static void init() {
        final TaskController taskController = new TaskController()

        before("/*", { req, res -> LOG.info("Received api call") })

        path("/api", { ->
            path("/tasks", { ->
                get("/all", { req, res -> taskController.getAll(res) })
                get("/:tid", { req, res -> taskController.getByTid(req, res) })
                post("", { req, res -> taskController.create(req, res) })
                put("", { req, res -> taskController.update(req, res) })
                patch("/:tid", { req, res -> taskController.markDoneAs(req, res) })
            })
        })

        options("/*",
                { request, response ->
                    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers")
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders)
                    }
                    String accessControlRequestMethod = request.headers("Access-Control-Request-Method")
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods", accessControlRequestMethod)
                    }
                    return "OK"
                })

        before({ request, response -> response.header("Access-Control-Allow-Origin", "*") })

        exception(NotFoundException, { e, req, res -> notFoundException(res, e) })
        exception(BadRequestException, { e, req, res -> badRequestException(res, e) })
    }
}
