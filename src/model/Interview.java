package model;
import java.util.Scanner;
import dao.InterviewDAO;

public class Interview {
    //function to start interview session
    public static  void takeInterview(User user, Scanner sc) {
       System.out.println("Welcome " + user.getUsername() + "Press Enter to Start Interview! 👆");
       sc.nextLine();
       System.out.println("❔Tell me about yourself!");
       String ans1 = sc.nextLine();
       System.out.println("❔" + InterviewDAO.getQuestion());
       String ans2 = sc.nextLine();
       System.out.println("❔" + InterviewDAO.getQuestion());
       String ans3 = sc.nextLine();
       System.out.println("❔" + InterviewDAO.getQuestion());
       String ans4 = sc.nextLine();
       System.out.println("❔Do you have any question?");
       String ans5 = sc.nextLine();
       String[] answers = {ans1, ans2, ans3, ans4, ans5 };
       InterviewDAO.submit(user,answers);




    }



}
