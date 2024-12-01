package rn32;

import java.util.List;

public interface StudentManager {
    void insert(Student student);
    List<Student> display();
    void update(int id,String name);
    void delete(int id);
}
