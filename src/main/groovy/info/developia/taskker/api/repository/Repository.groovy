package info.developia.taskker.api.repository

import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory

import java.lang.reflect.ParameterizedType
import java.util.function.Function

abstract class Repository<T> {
    private final Class<T> typeParameterClass = getTypeParameterClass()
    private static SqlSessionFactory sqlSessionFactory

    Repository() {
        sqlSessionFactory = RepositoryManager.getSession(typeParameterClass.getPackageName())
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTypeParameterClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
    }

    def <K> K repository(Function<T, K> name) {
        SqlSession session = sqlSessionFactory.openSession()
        try {
            K result = name.apply(session.getMapper(typeParameterClass))
            session.commit()
            return result
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage())
        } finally {
            session.close()
        }
    }
}
