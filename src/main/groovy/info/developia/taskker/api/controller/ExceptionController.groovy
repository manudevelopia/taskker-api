package info.developia.taskker.api.controller

import info.developia.taskker.api.exception.BadRequestException
import info.developia.taskker.api.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spark.Response

class ExceptionController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(Router)

    static String badRequestException(Response res, BadRequestException e) {
        LOG.error(e.getMessage())
        ErrorResponse errorResponse = new ErrorResponse(status: 400, message: e.getMessage())
        return buildResponseError(res, errorResponse)
    }

    static String notFoundException(Response res, NotFoundException e) {
        LOG.error(e.getMessage())
        ErrorResponse errorResponse = new ErrorResponse(status: 404, message: e.getMessage())
        return buildResponseError(res, errorResponse)
    }

    static class ErrorResponse {
        int status
        String message
    }
}
