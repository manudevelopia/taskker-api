package info.developia.taskker.api.mapper

import info.developia.taskker.api.model.Task
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface TaskMapper {
    @Select('''
       select t_tid tid,
              t_title title,
              t_description description,
              t_done done
       from tasks
       order by t_created_on desc
    ''')
    List<Task> getAll()

    @Select('''
        select t_tid tid, 
               t_title title, 
               t_description description,
               t_done done,
               t_created_on createdOn
        from tasks where t_tid = #{tid}
    ''')
    Task getById(String tid)

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

    @Update('''
        update tasks
        set t_done = #{done},
            t_updated_on = now()
        where t_tid = #{tid}
    ''')
    void markDoneAs(Task task)
}
