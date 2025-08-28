package app.loader;

import app.daos.CourseDAO;
import app.daos.StudentDAO;
import app.entities.Course;
import app.entities.Student;
import app.enums.StudentStatus;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Set;

public class DataLoader {

    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;

    public DataLoader(EntityManagerFactory emf) {
        this.studentDAO = new StudentDAO(emf);
        this.courseDAO = new CourseDAO(emf);
    }

    public void loadData() {
        Course math = courseDAO.createCourse(Course.builder()
                .name("Mathematics 101")
                .teacher("Dr. Smith")
                .semester("Fall 2023")
                .classroom("Room A1")
                .timeOfCourse("09:00 - 10:30")
                .build());

        Course cs = courseDAO.createCourse(Course.builder()
                .name("Computer Science 201")
                .teacher("Prof. Johnson")
                .semester("Spring 2024")
                .classroom("Lab B2")
                .timeOfCourse("11:00 - 12:30")
                .build());

        Course history = courseDAO.createCourse(Course.builder()
                .name("History 150")
                .teacher("Dr. White")
                .semester("Fall 2023")
                .classroom("Room C3")
                .timeOfCourse("14:00 - 15:30")
                .build());

        Student alice = Student.builder()
                .name("Alice Johnson")
                .email("alice@example.com")
                .status(StudentStatus.ACTIVE)
                .dateOfBirth(LocalDate.of(2000, 5, 12))
                .dateOfEnrollment(LocalDate.now())
                .courseIds(Set.of(math.getId(), cs.getId()))
                .build();

        Student bob = Student.builder()
                .name("Bob Smith")
                .email("bob@example.com")
                .status(StudentStatus.INACTIVE)
                .dateOfBirth(LocalDate.of(1998, 3, 8))
                .dateOfEnrollment(LocalDate.of(2016, 9, 1))
                .courseIds(Set.of(cs.getId()))
                .build();

        Student clara = Student.builder()
                .name("Clara White")
                .email("clara@example.com")
                .status(StudentStatus.GRADUATED)
                .dateOfBirth(LocalDate.of(1995, 11, 20))
                .dateOfEnrollment(LocalDate.of(2014, 9, 1))
                .courseIds(Set.of(history.getId()))
                .build();

        studentDAO.createStudent(alice);
        studentDAO.createStudent(bob);
        studentDAO.createStudent(clara);
    }
}
