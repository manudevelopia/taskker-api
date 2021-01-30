package info.developia.taskker.api.controller


import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static spark.Spark.exception

class ExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(Router)
    private static final JsonTransformer transformer = new JsonTransformer()

    static void init() {
        exception(NotFoundException, { exception, req, res ->
            ErrorResponse errorResponse = new ErrorResponse(status: 404, message: exception.getMessage())
            LOG.error(exception.getMessage())
            res.type('application/json')
            res.status(errorResponse.status)
            res.body(transformer.render(errorResponse))
        })
    }

    static class ErrorResponse {
        int status
        String message
    }
}
