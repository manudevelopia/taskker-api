package info.developia.taskker.api.repository


import org.apache.ibatis.datasource.pooled.PooledDataSource
import org.apache.ibatis.exceptions.PersistenceException
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

import javax.sql.DataSource
import java.lang.reflect.ParameterizedType
import java.util.function.Function

class Repository<T> {
    private static SqlSessionFactory sqlSessionFactory
    private final Class<T> typeParameterClass

    Repository() {
        sqlSessionFactory = buildSqlSessionFactory()
        typeParameterClass = getTypeParameterClass()
    }

    SqlSessionFactory buildSqlSessionFactory() {
        DataSource dataSource = new PooledDataSource(
                "org.postgresql.Driver",
                System.getenv().get("DATABASE_URL"),
                System.getenv().get("DATABASE_USERNAME"),
                System.getenv().get("DATABASE_PASSWORD"))

        Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource)
        Configuration configuration = new Configuration(environment)
        configuration.addMappers("info.developia.taskker.api.mapper")

        return new SqlSessionFactoryBuilder().build(configuration)
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
