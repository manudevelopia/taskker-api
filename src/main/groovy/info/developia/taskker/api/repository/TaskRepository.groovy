package info.developia.taskker.api.repository

import info.developia.taskker.api.exception.TaskServiceException
import info.developia.taskker.api.mapper.TaskMapper
import info.developia.taskker.api.model.Task
import org.apache.ibatis.exceptions.PersistenceException

class TaskRepository extends Repository<TaskMapper> {

    List<Task> getAll() {
        try {
            return repository({ TaskMapper tm -> tm.getAll() });
        } catch (PersistenceException e) {
            throw new TaskServiceException(e.getMessage());
        }
    }

    Optional<Task> getById(Long id) {
        try {
            return Optional.ofNullable(repository({ TaskMapper tm -> tm.getById(id) }))
        } catch (PersistenceException e) {
            throw new TaskServiceException(e.getMessage())
        }
    }

    void create(Task task) {
        try {
            repository({ TaskMapper tm -> tm.create(task) })
        } catch (PersistenceException e){
            throw new TaskServiceException(e.getMessage())
        }
    }
}
