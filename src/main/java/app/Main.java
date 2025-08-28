package app;

import app.config.HibernateConfig;
import app.daos.CourseDAO;
import app.daos.StudentDAO;
import app.entities.Course;
import app.entities.Student;
import app.enums.StudentStatus;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static app.entities.Course.getCourses;
import static app.entities.Student.getStudents;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        StudentDAO studentDAO = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);

        List<Student> students = getStudents();
        students.forEach(studentDAO::createStudent);

        List<Course> courses = getCourses();
        courses.forEach(courseDAO::createCourse);

        Student studentToUpdate = students.get(0);
        studentToUpdate.setStatus(StudentStatus.SUSPENDED);
        studentDAO.updateStudent(studentToUpdate);

        Course courseToUpdate = courses.get(0);
        courseToUpdate.setSemester("Summer 2023");
        courseDAO.update(courseToUpdate);

        studentDAO.deleteById(2);
        courseDAO.deleteById(2);

        studentDAO.findAll().forEach(System.out::println);
        courseDAO.findAll().forEach(System.out::println);

        emf.close();


    }
}