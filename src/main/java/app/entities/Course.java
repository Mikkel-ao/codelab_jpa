package app.entities;
import app.enums.StudentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String teacher;
    private String semester;
    private String classroom;


    private String timeOfCourse() {
        return null;
    }

    public static List<Course> getCourses() {
        return List.of(
                Course.builder()
                        .name("Mathematics 101")
                        .teacher("Dr. Smith")
                        .semester("Fall 2023")
                        .classroom("Room A1")
                        .build(),

                Course.builder()
                        .name("Computer Science 201")
                        .teacher("Prof. Johnson")
                        .semester("Spring 2024")
                        .classroom("Lab B2")
                        .build(),

                Course.builder()
                        .name("History 150")
                        .teacher("Dr. White")
                        .semester("Fall 2023")
                        .classroom("Room C3")
                        .build()
        );
    }

}
