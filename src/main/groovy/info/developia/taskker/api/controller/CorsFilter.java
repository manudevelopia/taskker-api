package info.developia.taskker.api.controller;

import spark.Filter;
import spark.Request;
import spark.Response;

public final class CorsFilter implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Request-Method", "GET,PUT,POST,DELETE,OPTIONS,PATCH");
        response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        response.header("Access-Control-Allow-Credentials", "true");
    }
}
