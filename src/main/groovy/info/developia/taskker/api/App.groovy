package info.developia.taskker.api

import info.developia.taskker.api.controller.Router

import static spark.Spark.port

class App {
    static void main(String[] args) {
        config()
        Router.init()
    }

    private static config() {
        port(Integer.valueOf(System.getProperty("server.port", "8800")))
    }
}
