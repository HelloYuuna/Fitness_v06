package scit.dao;

import scit.vo.FitnessVO;

import java.util.List;

/**
 * projectName     :Fitness_v06
 * fileName        :FitnessMapper
 * author          :yuuna 05/24
 * since           :2022/04/27
 */
public interface FitnessMapper {
    public int regist(FitnessVO vo);

    public FitnessVO findById(String usrid);

    public List<FitnessVO> findAll();

    public int update(FitnessVO vo);

    public int delete(String usrid);

    public int getCount();
}
