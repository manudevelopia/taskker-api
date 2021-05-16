package info.developia.taskker.api.repository

import io.github.cdimascio.dotenv.Dotenv
import org.apache.ibatis.datasource.pooled.PooledDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

import javax.sql.DataSource

class RepositoryManager {
    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory buildSqlSessionFactory(String mappersPackageName) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load()
        DataSource dataSource = new PooledDataSource(
                "org.postgresql.Driver",
                dotenv.get("DATABASE_URL_CONN"),
                dotenv.get("DATABASE_USERNAME"),
                dotenv.get("DATABASE_PASSWORD"))

        Environment environment = new Environment("Default", new JdbcTransactionFactory(), dataSource)
        Configuration configuration = new Configuration(environment)
        configuration.addMappers(mappersPackageName)

        return new SqlSessionFactoryBuilder().build(configuration)
    }

    static SqlSessionFactory getSession(String mappersPackageName) {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = buildSqlSessionFactory(mappersPackageName)
        }
        return sqlSessionFactory
    }
}
