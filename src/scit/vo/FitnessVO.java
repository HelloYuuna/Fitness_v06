package scit.vo;

import java.io.Serializable;

/**
 * projectName     :Fitness_v05
 * fileName        :FitnessVO
 * author          :yuuna 05/03
 * since           :2022/04/27
 */
public class FitnessVO {
    /* default ID */
//    private static final long serialVersionUID = 1L;

    private String usrId;
    private String usrName;
    private double height;
    private double weight;

    /* VO에서 바로 계산 */
    private double bmi;
    private String bmiResult;               // DB 에 저장 >> 문자열
    private String joinDate;

    public FitnessVO(String usrId, String usrName, double height, double weight){
        this.usrId = usrId;
        this.usrName = usrName;
        this.height = height;
        this.weight = weight;

        calcBmi();
    }

    private void calcBmi(){
        double tmp;
        tmp = (height * 0.01);
        bmi = weight/(tmp * tmp);

        setResult();
    }

    private void setResult(){
        if(bmi > 35) {bmiResult = "고도비만";}
        else if (bmi > 30) {bmiResult = "중도비만";}
        else if (bmi > 25) {bmiResult = "경도비만";}
        else if (bmi > 23) {bmiResult = "과체중";}
        else if (bmi > 18.5) {bmiResult = "정상";}
        else {bmiResult = "저체중";}
    }

    public void setUsrId(String usrId){
        this.usrId = usrId;
    }

    public String getUsrId(){
        return usrId;
    }

    public void setUsrName(String usrName){
        this.usrName = usrName;
    }

    public String getUsrName(){
        return usrName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calcBmi();
    }

    /* 데이터베이스와 연동을 위해 BMI BMIRESULT도 만들어준다 */
    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
        calcBmi();
    }

    public String getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(String bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return String.format("%s(%s): %6.2fcm %6.2fkg  bmi:%6.2f (%s)", usrName, usrId, height, weight, bmi, bmiResult);
    }

}

