package scit.ui;

import scit.dao.FitnessDAO;
import scit.vo.FitnessVO;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * projectName     :Fitness_v06
 * fileName        :FitnessUI
 * author          :yuuna 05/24
 * since           :2022/04/27
 */
public class FitnessUI {
    FitnessDAO dao = new FitnessDAO();
    Scanner sc = new Scanner(in);
    String choice;

    /**
     * 메뉴 선택
     */
    public void start() {
        while (true) {
            menu();
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    input();
                    break;
                case "2":
                    output();
                    break;
                case "3":
                    select();
                    break;
                case "4": delete(); break;
                case "5": update(); break;
                case "0":
                    out.println("시스템을 종료해!");
                    return;
                default: out.println("잘못된 선택이야!");
            }

        }
    }


    /**
     * 메뉴 호출
     */
    public void menu(){
        out.println("------------------------");
        out.println("\t회원 관리 프로그램");
        out.println("------------------------");
        out.println("\t 1. 회원  가입");
        out.println("\t 2. 전체  출력");
        out.println("\t 3. 회원  검색");
        out.println("\t 4. 회원  삭제");
        out.println("\t 5. 회원  수정");
        out.println("\t 0. 이거  종료");
        out.println("------------------------");
        out.print("\t 선택> ");
    }

    private boolean idCheck(String usrid) {
        FitnessVO vo = dao.findById(usrid);
        return vo != null;
//        if(vo != null) {return true;}
//        return false;
    }


    /**
     *  회원가입
     */
    public void input() {
        out.println("\n\t[ 신규 회원 등록 ]");

        while(true)
        {
            out.print("회원 아아디: ");
            String usrId = sc.nextLine();

            /* 중복 usrId CHECK */
            if(idCheck(usrId)) {
                out.println("이미 존재하는 아이디야! 다시 입력해줘!");

            } else {
                out.print("회원 이름: ");
                String usrName = sc.nextLine();

                out.print("키: ");
                double height = sc.nextDouble();

                out.print("몸무게: ");
                double weight = sc.nextDouble();
                sc.nextLine();

                FitnessVO fitnessVO = new FitnessVO(usrId, usrName, height, weight);
                int result = dao.regist(fitnessVO);
                out.println("\t\t[" + result + "명 회원가입 완료! ]\n");

                return;
            }

        }

    }

    /**
     * 전체 출력
     */
    public void output(){
        int fitnessCount = dao.getCount();
        if(fitnessCount <= 0) {
            out.println("우선 가입부터 하자!");
            return;
        }

        List<FitnessVO> list = dao.findAll();
        list.forEach(out::println);
        out.println();
    }

    /**
     * 특정 회원 검색
     */
    public void select(){
        /* 아무도 없다면? */
        int fitnessCount = dao.getCount();
        if(fitnessCount <= 0) {
            out.println("우선 가입부터 하자!");
            return;
        }

        while(true) {
            out.print("찾는 회원의 아이디가 뭐야? >");
            String usrId = sc.nextLine();

            FitnessVO fitness = dao.findById(usrId);
            if(!idCheck(usrId)) {
                out.println("존재하지 않는 회원이야! 다시 입력해줘!");
                continue;
            }

            out.println("\n너가 찾는 회원이야!");
            out.println(fitness);

            out.println();
            return;
        }
    }

    public void delete(){
        /* 아무도 없다면? */
        /* getCount로 변경
         * select count(*) from fitness
         */
//        List<FitnessVO> list = dao.findAll();
        int fitnessCount = dao.getCount();
        if(fitnessCount <= 0) {
            out.println("우선 가입부터 하자!");
            return;
        }

        while(true) {
            out.print("탈퇴할 회원의 아이디가 뭐야? >");
            String usrId = sc.nextLine();

            FitnessVO fitness = dao.findById(usrId);
            if (fitness == null) {
                out.println("존재하지 않는 회원이야! 다시 입력해줘!");
                continue;
            }

            out.println("\n" + fitness);
            out.print("이 회원 정말 탈퇴해?(y/n) >");
            String answer = sc.nextLine();

            if (answer.equals("n") || answer.equals("N")) {
                out.println("탈퇴 취소할께!");
                return;

            } else if (answer.equals("y") || answer.equals("Y")) {
                int result = dao.delete(usrId);

                if(result == 1) {
                    out.println("정상적으로 탈퇴되었어!");
                } else {
                    out.println("탈퇴 처리에 실패했어 ㅠㅠ");
                }
                return;

            } else {
                out.println("y/n로 대답해줘!");
            }

        }
    }

    public void update(){
        /* 아무도 없다면? */
        int fitnessCount = dao.getCount();
        if(fitnessCount <= 0) {
            out.println("우선 가입부터 하자!");
            return;
        }

        while(true) {
            out.print("수정할 회원의 아이디가 뭐야? >");
            String usrId = sc.nextLine();

            FitnessVO fitness = dao.findById(usrId);
            if (fitness == null) {
                out.println("존재하지 않는 회원이야! 다시 입력해줘!");
                continue;
            }

            out.println("너가 수정할 회원이야!");
            out.println("\n" + fitness);

            out.print("새롭게 수정할 키를 알려줘! >");
            double height = sc.nextDouble();

            out.print("새롭게 수정할 몸무게를 알려줘! >");
            double weight = sc.nextDouble();
            sc.nextLine();

            fitness.setHeight(height);
            fitness.setWeight(weight);
            int result = dao.update(fitness);

            if(result == 1) {
                out.println(fitness);
                out.println("\t\t[ 회원수정 완료! ]\n");

            } else {
                out.println("탈퇴 처리에 실패했어 ㅠㅠ");

            }

            return;
        }
    }

}
