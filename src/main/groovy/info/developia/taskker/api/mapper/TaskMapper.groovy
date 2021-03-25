package info.developia.taskker.api.mapper

import info.developia.taskker.api.model.Task
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface TaskMapper {
    @Select('''
       select t_title title,
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

    @Insert('''
        insert into tasks(t_tid, t_title, t_description, t_created_on) 
        values (#{tid}, #{title}, #{description}, now())
    ''')
    void create(Task task)

    @Update('''
        update tasks
        set t_title = #{title}, 
            t_description = #{description}, 
            t_updated_on = now()
        where t_tid = #{tid}
    ''')
    void update(Task task)
}
