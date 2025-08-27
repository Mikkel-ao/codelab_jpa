package app.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    private int id;
    private String name;
    private String teacher;
    private String semester;
    private String classroom;


    private String timeOfCourse() {
        return null;
    }


}
