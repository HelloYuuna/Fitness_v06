package scit.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * MybatisSqlSsetionFactory
 * MyBatis가 JDBC코드를 실행하는데 필요한 전반에 걸친 세팅을 한다.
 * projectName     :Fitness_v06
 * fileName        :MybatisConfig
 * author          :yuuna 22/05/24
 * since           :2022/05/19
 */
public class MybatisConfig {
    private static SqlSessionFactory sqlSessionFactory;

    /* statoc block 을 포함하므로 이 객체는 인스턴스화 하지 않는다.
     * 따라서 암묵적으로 생기는 퍼블릭 생성자의 생성을 막기위해
     * private 생성자를 명시적으로 생성해준다.
     */
    private MybatisConfig() { }

    /*
     스태틱 블록 (생성자와 유사한 기능)
     mybatis-config.xml 을
     SqlSessionFactoryBuilder 로 메모리에 올림
     */
    static {
        String resource = "mybatis-config.xml";

        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
