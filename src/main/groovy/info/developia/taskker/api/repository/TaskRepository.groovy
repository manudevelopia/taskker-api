package info.developia.taskker.api.repository

import info.developia.taskker.api.exception.TaskServiceException
import info.developia.taskker.api.mapper.TaskMapper
import info.developia.taskker.api.model.Task
import info.developia.taskker.api.repository.Repository
import org.apache.ibatis.exceptions.PersistenceException

class TaskRepository extends Repository<TaskMapper> {

    List<Task> getAll() {
        try {
            return repository({ TaskMapper tm -> tm.getAll() });
        } catch (PersistenceException e) {
            throw new TaskServiceException(e.getMessage());
        }
    }

    Task getById(Long id) {
        try {
            return repository({ TaskMapper tm -> tm.getById(id) })
        } catch (PersistenceException e) {
            throw new TaskServiceException(e.getMessage())
        }
    }
}
