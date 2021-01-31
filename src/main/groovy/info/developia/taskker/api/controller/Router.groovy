package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static spark.Spark.*

class Router {
    private final static Logger LOG = LoggerFactory.getLogger(Router)

    static void init() {
        final TaskController taskController = new TaskController()
        final ExceptionController exceptionController = new ExceptionController()

        before("/*", { req, res -> LOG.info("Received api call") });

        path("/api", { ->
            path("/tasks", { ->
                get("/all", { req, res -> taskController.getAll(res) })
                get("/:id", { req, res -> taskController.getById(req, res) })
            })
        })

        exception(NotFoundException, { e, req, res -> exceptionController.notFoundException(res, e) })
    }
}
