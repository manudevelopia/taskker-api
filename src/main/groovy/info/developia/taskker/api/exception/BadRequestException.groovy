package info.developia.taskker.api.exception

class BadRequestException extends RuntimeException{
    BadRequestException(String message) {
        super(message)
    }
}
