package developia.info.taskker

import developia.info.taskker.controller.Router
import developia.info.taskker.model.Task
import kong.unirest.GenericType
import kong.unirest.HttpResponse
import kong.unirest.Unirest
import spock.lang.Specification

class TaskSpec extends Specification {

    def setupSpec() {
        Router.init()
    }

    def "get all should return all tasks"() {
        given:
        String allTasksUrl = 'http://localhost:4567/api/tasks/all'
        when:
        HttpResponse<List<Task>> response = Unirest.get(allTasksUrl).asObject(new GenericType<List<Task>>() {})

        then:
        response.status == 200
        !response.getBody().isEmpty()
    }
}
