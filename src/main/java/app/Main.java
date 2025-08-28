package app;

import app.config.HibernateConfig;
import app.daos.CourseDAO;
import app.daos.StudentDAO;
import app.entities.Course;
import app.entities.Student;
import app.enums.StudentStatus;
import app.loader.DataLoader;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        StudentDAO studentDAO = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);

        DataLoader dataLoader = new DataLoader(emf);
        dataLoader.loadData();

        List<Student> students = studentDAO.findAll();
        List<Course> courses = courseDAO.findAll();

        Student studentToUpdate = students.get(0);
        studentToUpdate.setStatus(StudentStatus.SUSPENDED);
        studentDAO.updateStudent(studentToUpdate);

        Course courseToUpdate = courses.get(0);
        courseToUpdate.setSemester("Summer 2023");
        courseDAO.update(courseToUpdate);

        studentDAO.deleteById(2);
        courseDAO.deleteById(2);

        System.out.println("All students:");
        students.forEach(System.out::println);

        System.out.println("All courses:");
        courses.forEach(System.out::println);

        // List all courses for a specific student
        int studentId = students.get(0).getId(); // dynamically pick first student
        List<Course> coursesByStudentId = courseDAO.findCoursesByStudentId(studentId);
        System.out.println("\nCourses for student: " + students.get(0).getName());
        coursesByStudentId.forEach(c -> System.out.println(c.getName() + " (ID: " + c.getId() + ")"));

        // List all students for a specific course
        int targetCourseId = courses.get(0).getId(); // dynamically pick first course
        List<Student> studentsInCourse = studentDAO.findByCourseId(targetCourseId);
        System.out.println("\nStudents enrolled in course: " + courses.get(0).getName());
        studentsInCourse.forEach(s -> System.out.println(s.getName() + " (ID: " + s.getId() + ")"));

        emf.close();
    }
}
