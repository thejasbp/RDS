package rn32;

//import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class StudentManagerImpl implements StudentManager {
    private static final Logger logger= LogManager.getLogger(StudentManagerImpl.class);

    public StudentManagerImpl()
    {
        String createtable="create table if not exists stuprac(stuid int primary key,stuname varchar(255),stuage int)";
        try(Connection con=DBConnection.getConnection();
            Statement sta=con.createStatement())
        {
            sta.execute(createtable);
            logger.info("The table created");
        }
        catch (SQLException e)
        {
            logger.info("The Error in creating the table");
            System.out.println("Error in Creating the table "+e.getMessage());
        }
    }

    @Override
    public void insert(Student student) {
        String inserttable="insert into stuprac (stuid,stuname,stuage) values (?,?,?)";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement pstm=con.prepareStatement(inserttable))
        {

            pstm.setInt(1,student.getId());
            pstm.setString(2,student.getName());
            pstm.setInt(3,student.getAge());
            pstm.executeUpdate();
            logger.info("The values are inserted");
        }catch (SQLException e)
        {
            System.out.println("Error int inserting the table "+e.getMessage());
        }
    }

    @Override
    public List<Student> display() {
        String displaytable="select * from stuprac";
        List<Student> stu=new ArrayList<>();
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pstm=con.prepareStatement(displaytable))
        {
            ResultSet rs=pstm.executeQuery();
            while(rs.next())
            {

                stu.add(new Student(


                        rs.getInt("stuid"),
                        rs.getString("stuname"),
                        rs.getInt("stuage")
                        )
                );

            }
            if(rs!=null)
            {
                System.out.println("the Student details are:\n");
                for(Student stud:stu)
                {
                    System.out.println(stud);
                }
            }
            else {
                System.out.println("The Students are empty");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error in displaying " +e.getMessage());
        }
        return stu;
    }

    @Override
    public void update(int id, String name) {
        String updatetable="update stuprac set stuname=? where stuid=?";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pstm=con.prepareStatement(updatetable))
        {
            pstm.setString(1,name);
            pstm.setInt(2,id);
            int rowsa=pstm.executeUpdate();
            if(rowsa>0)
            {
                System.out.println("Updated Successfully");
            }
            else {
                System.out.println("Error in Updating");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error in updating "+e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
    String deletetable="delete from stuprac where stuid=?";
    try(Connection con=DBConnection.getConnection();
    PreparedStatement pstm=con.prepareStatement(deletetable))
    {
        pstm.setInt(1,id);
        int rowsaffected=pstm.executeUpdate();
        if(rowsaffected>0)
        {
            System.out.println("Deleted Successfully");

        }
        else {
            System.out.println("Error in deleting the query");
    }
    }
    catch (SQLException e)
    {
        System.out.println("Error in deleteing "+e.getMessage());
    }
    }
}
