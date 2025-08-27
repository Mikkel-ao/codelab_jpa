package app.daos;

import app.entities.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CourseDAO {
    private final EntityManagerFactory emf;

    public CourseDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Course create(Course course) {
        return null;
    }

    public List<Course> findById(int id) {
        return null;
    }

    public List<Course>findAll() {
        return null;
    }

    public Course update(Course course) {
        return null;
    }

    public boolean deleteById(int id) {
        return false;
    }


}
