import dao.SessionDAO;
import dao.UserDAO;
import model.User;
import model.Interview;
import dao.InterviewDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //creating object for user class to store data
        UserDAO user = new UserDAO();

        System.out.println("### Welcome to Interview  Master! ###");

        while (true) {

            System.out.println("\nPlease select your role:");
            System.out.println("  1.  User");
            System.out.println("  2.  Admin");
            System.out.println("  0. Exit");
            System.out.print("\n🔷 Choose an option: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        userFlow(sc);
                        break;

                    case 2:
                        adminFlow(sc);
                        break;

                    case 0:
                        System.out.println("Exiting....");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Enter a valid choice! Try Again---");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                sc.nextLine(); // clear invalid input
            }

        }
    }

    //function to handle user option 1--------------------------------------
    public static void userFlow(Scanner sc) {
        //creating object of UserDAO
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("\n----------------------------------------------");
            System.out.println("👤 Register or Login to take Mock Interview");
            System.out.println("  1.  Register (New User)");
            System.out.println("  2.  Login");
            System.out.println("  3.  Main Menu");
            System.out.println("  0.  Exit");
            System.out.print("\n🔑 Enter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter your username 👤: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        System.out.println("Create you password 🔐: ");
                        String pass = sc.nextLine();
                        System.out.println("Registering you.....");
                        User registerUser = new User(name, pass);
                        boolean isRegistered = userDAO.createUser(registerUser);
                        if (isRegistered) {
                            System.out.println("Registration Done✔️");
                        }
                        System.out.println("Login to give Interview!");
                        break;

                    case 2:
                        System.out.println("--Username-- :");
                        sc.nextLine();
                        String username = sc.nextLine();
                        System.out.println("--Password-- :");
                        String password = sc.nextLine();
                        System.out.println("Checking#########");

                        User user = new User(username, password);
                        boolean isLoggedIn = userDAO.checkUser(user);
                        if (isLoggedIn) System.out.println("✅ Logged in Successfully! Welcome back,  ⭐\n");
                        else {
                            System.out.println("User Not Found!");
                            break;
                        }
                        user.setId(UserDAO.getUserId(user));
                        Interview.takeInterview(user, sc);
                        break;

                    case 0:
                        System.out.println("Exiting....");
                        System.exit(0);
                        break;
                    case 3:
                        System.out.println("🔁 Returning to main menu...");
                        return;

                    default:
                        System.out.println("Enter a valid choice!");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                sc.nextLine();
            }
        }
    }

    //function to handle Admin option 2--------------------------------------
    public static void adminFlow(Scanner sc) {
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("\n===== 👑 Admin Panel =====");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. Update User");
            System.out.println("4. View Interview Sessions");
            System.out.println("5. Clear All Sessions");
            System.out.println("6. View All Interview Questions");
            System.out.println("7. Add New Question");
            System.out.println("8. Delete Question");
            System.out.println("0. Exit Admin Panel");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        userDAO.viewAllUsers();
                        break;

                    case 2:
                        System.out.println("Enter id of user to delete");
                        if (sc.hasNextInt()) {
                            int userId = sc.nextInt();
                            userDAO.deleteUser(userId);
                        } else {
                            System.out.println("❌ Invalid input! Please enter a valid user ID.");
                            sc.nextLine();
                        }
                        break;

                    case 3:
                        UserDAO.updateUserById(sc);
                        break;

                    case 4:
                        SessionDAO.viewSessions(sc);
                        break;

                    case 5:
                        SessionDAO.clearAllSessions();
                        break;

                    case 6:
                        InterviewDAO.getAllQuestions();
                        break;

                    case 7:
                        System.out.print("Enter new question: ");
                        String newQuestion = sc.nextLine();
                        InterviewDAO.addQuestion(newQuestion);
                        break;

                    case 8:
                        System.out.print("Enter question ID to delete: ");
                        if (sc.hasNextInt()) {
                            int qid = sc.nextInt();
                            InterviewDAO.deleteQuestion(qid);
                        } else {
                            System.out.println("❌ Invalid input! Please enter a valid question ID.");
                            sc.nextLine();
                        }
                        break;

                    case 0:
                        System.out.println("Exiting....");
                        return;

                    default:
                        System.out.println("❌ Enter a valid choice!");
                }
            } else {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                sc.nextLine();
            }
        }
    }
}
