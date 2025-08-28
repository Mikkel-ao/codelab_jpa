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

    @ToString.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> courseIds;

}