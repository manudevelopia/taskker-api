package info.developia.taskker.api.model

import java.time.LocalDateTime

class Task {
    String tid
    String title
    String description
    boolean done
    LocalDateTime createdOn
    List<Task> subTasks
    User user
}
