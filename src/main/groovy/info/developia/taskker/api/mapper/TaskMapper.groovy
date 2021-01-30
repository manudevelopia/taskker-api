package info.developia.taskker.api.mapper

import info.developia.taskker.api.model.Task
import org.apache.ibatis.annotations.Select

interface TaskMapper {
    @Select('''
        select t_title       title,
               t_description description
               from tasks
               ''')
    List<Task> getAll()

    @Select('''
            select t_title title, 
            t_description description 
            from tasks where t_id = #{id}
    ''')
    Task getById(Long id)
}
