package rn32;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
//import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
       try(Connection con=DBConnection.getConnection())
       {
           System.out.println("Database Connected");
           logger.info("Database Connected");
       }
       catch (SQLException e)
       {
           System.out.println("Database is not Connected "+e.getMessage());
           logger.error("Database is not Connected: {}", e.getMessage());
       }
        Scanner scanner =new Scanner(System.in);
        StudentManagerImpl manager=new StudentManagerImpl();
        while(true)
        {
            System.out.println("1.Insert\n2.Display\n3.Update\n4.Delete\n5.Exit\nEnter your choice:");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    try
                    {
                        System.out.println("Enter the Stu ID:");
                        int id=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the Stu name:");
                        String name=scanner.nextLine();
                        System.out.println("Enter the Stu age");
                        int age= scanner.nextInt();
                        scanner.nextLine();
                        Student stu=new Student(id,name,age);
                        manager.insert(stu);

                    }
                    catch (InputMismatchException e)
                    {
//                        System.out.println("Invalid Input "+e.getMessage());
                        logger.warn("Invalid input: {}", e.getMessage());
                        scanner.next();
                    }
                    break;
                case 2:
                    manager.display();
                    break;
                case 3:
                    try{
                        System.out.println("Enter the Stu Id to update:");
                        int id1=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the Stu Name to update");
                        String name1=scanner.nextLine();
                        manager.update(id1,name1);
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Innvalid Input "+e.getMessage());
                        scanner.next();
                    }
                    break;
                case 4:
                    try
                    {
                        System.out.println("Enter the Stu Id to Delete:");
                        int id2=scanner.nextInt();
                        scanner.nextLine();
                        manager.delete(id2);
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Invaluid Input "+e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid Entry");

            }
        }




    }
}