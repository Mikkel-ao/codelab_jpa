package app.daos;

import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class StudentDAO {
    private final EntityManagerFactory emf;

    public StudentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createStudent(Student student) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

        }
        em.getTransaction().commit();
    } finally {
        em.close();
    }

    public List<Student> findAll() {
        return null;
    }

    public Student updateStudent(Student student) {
        return null;
    }

    public boolean deleteById(int id) {
        return false;
    }

    public List<Student>findByCourseId(int courseId) {
        return null;
    }
}
