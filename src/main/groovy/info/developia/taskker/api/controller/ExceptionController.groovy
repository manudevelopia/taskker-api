package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spark.Response

class ExceptionController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(Router)

    static void notFoundException(Response res, NotFoundException e) {
        LOG.error(e.getMessage())
        ErrorResponse errorResponse = new ErrorResponse(status: 404, message: e.getMessage())
        buildResponse(res, errorResponse.getStatus(), errorResponse)
    }

    static class ErrorResponse {
        int status
        String message
    }
}
