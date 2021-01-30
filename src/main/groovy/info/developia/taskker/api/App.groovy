package info.developia.taskker.api

import info.developia.taskker.api.controller.Router

import static spark.Spark.port

class App {
    static void main(String[] args) {
        Optional.of(System.getProperty("server.port", "8800"))
                .ifPresent({ portNumber -> port(Integer.valueOf(portNumber)) })
        Router.init()
    }
}
