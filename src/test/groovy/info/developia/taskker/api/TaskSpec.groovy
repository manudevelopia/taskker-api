package info.developia.taskker.api

import info.developia.taskker.api.controller.Router
import info.developia.taskker.api.model.Task
import kong.unirest.GenericType
import kong.unirest.HttpResponse
import kong.unirest.Unirest
import spock.lang.Shared
import spock.lang.Specification

import static spark.Spark.port

class TaskSpec extends Specification {

    @Shared
    int port = 4567
    String baseUrl = "http://localhost:$port"

    def setupSpec() {
        port(port)
        Router.init()
    }

    def "get all should return all tasks"() {
        given:
        String allTasksUrl = "$baseUrl/api/tasks/all"
        when:
        HttpResponse<List<Task>> response = Unirest.get(allTasksUrl).asObject(new GenericType<List<Task>>() {})

        then:
        response.status == 200
        !response.getBody().isEmpty()
    }

    def "create a new task"() {
        given:
        String json = '{"title" : "task 10 title" , "description" : "task 10 description" }'
        when:
        HttpResponse response = Unirest.post("$baseUrl/api/tasks").body(json).asEmpty()
        then:
        response.getStatus() == 201
    }

    def "update an existing task"() {
        given:
        String json = '{ "tid": "8ac6e3a1d96c42a881c25e5e07b9383b", "title" : "update task 10 title", "description" : "update task 10 description" }'
        when:
        HttpResponse response = Unirest.put("$baseUrl/api/tasks").body(json).asEmpty()
        then:
        response.getStatus() == 200
    }
}
