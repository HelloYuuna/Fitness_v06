package scit.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import scit.vo.FitnessVO;

import java.util.List;

/**
 * 데이터베이스 연동 준비
 * projectName     :Fitness_v06
 * fileName        :FitnessDAO
 * author          :yuuna
 * since           :2022/05/25
 */
public class FitnessDAO {
    private final SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

    /* 회원가입 */
    public int regist(FitnessVO vo) {
        SqlSession session = factory.openSession();                            // 외부에 DB가있다면 try catch문으로 서버 딜레이를 잡아준다
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

        int reuslt = mapper.regist(vo);
        session.commit();

        return reuslt;
    }

    public FitnessVO findById(String usrId) {
        SqlSession session = factory.openSession();
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

        return mapper.findById(usrId);
    }

    public List<FitnessVO> findAll() {
        SqlSession session = factory.openSession();
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

//        List<FitnessVO> fitnessList = mapper.findAll();
//
//        return fitnessList;
        return mapper.findAll();
    }

    public int delete(String usrId) {
        SqlSession session = factory.openSession();
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

        int result = mapper.delete(usrId);

        if(result == 1) {
            session.commit();
            return 1;
        }
        session.rollback();

        return 0;
    }

    public int update(FitnessVO fitness) {
        SqlSession session = factory.openSession();
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

        int result = mapper.update(fitness);

        if(result == 1) {
            session.commit();
            return 1;
        }

        return 0;
    }

    public int getCount() {
        SqlSession session = factory.openSession();
        FitnessMapper mapper = session.getMapper(FitnessMapper.class);

        // 0 ~ 의 값을 갖음
        return mapper.getCount();
    }
}
