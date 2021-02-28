package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static info.developia.taskker.api.controller.ExceptionController.badRequestException
import static info.developia.taskker.api.controller.ExceptionController.notFoundException
import static spark.Spark.before
import static spark.Spark.exception
import static spark.Spark.get
import static spark.Spark.path
import static spark.Spark.post

class Router {
    private final static Logger LOG = LoggerFactory.getLogger(Router)

    static void init() {
        final TaskController taskController = new TaskController()

        before("/*", { req, res -> LOG.info("Received api call") });

        path("/api", { ->
            path("/tasks", { ->
                get("/all", { req, res -> taskController.getAll(res) })
                get("/:id", { req, res -> taskController.getById(req, res) })
                post("/", { req, res -> taskController.create(req, res) })
            })
        })

        exception(NotFoundException, { e, req, res -> notFoundException(res, e) })
        exception(BadRequestException, { e, req, res -> badRequestException(res, e) })
    }
}
