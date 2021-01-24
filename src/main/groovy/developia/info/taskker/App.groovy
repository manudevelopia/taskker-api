package developia.info.taskker

import developia.info.taskker.controller.Router

import static spark.Spark.port

class App {
    static void main(String[] args) {
        Optional.ofNullable(System.getProperty("server.port"))
                .ifPresent({ portNumber -> port(Integer.valueOf(portNumber)) })
        Router.init()
    }
}
