package app.entities;

import app.enums.StudentStatus;
import jakarta.persistence.*;
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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
    LocalDate dateOfBirth;
    LocalDate dateOfEnrollment;



    public static List<Student> getStudents() {
        return List.of(
                Student.builder()
                        .name("Alice Johnson")
                        .email("alice@example.com")
                        .status(StudentStatus.ACTIVE)
                        .dateOfBirth(LocalDate.of(2000, 5, 12))
                        .dateOfEnrollment(LocalDate.now())
                        .build(),

                Student.builder()
                        .name("Bob Smith")
                        .email("bob@example.com")
                        .status(StudentStatus.INACTIVE)
                        .dateOfBirth(LocalDate.of(1998, 3, 8))
                        .dateOfEnrollment(LocalDate.of(2016, 9, 1))
                        .build(),

                Student.builder()
                        .name("Clara White")
                        .email("clara@example.com")
                        .status(StudentStatus.GRADUATED)
                        .dateOfBirth(LocalDate.of(1995, 11, 20))
                        .dateOfEnrollment(LocalDate.of(2014, 9, 1))
                        .build()
        );
    }


}