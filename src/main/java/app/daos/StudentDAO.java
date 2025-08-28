package app.daos;

import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Data;

import java.util.List;


public class StudentDAO {
    private final EntityManagerFactory emf;

    public StudentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Student createStudent(Student student) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> findAll() {
        try (EntityManager em = getEntityManager()) {
            return em.createQuery("SELECT s FROM Student s", Student.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Student updateStudent(Student student) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Student updatedStudent = em.merge(student);
            em.getTransaction().commit();
            return updatedStudent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student == null) {
                System.out.println("Student with ID: " + id + " not found.");
                em.getTransaction().rollback();
                return false;
            }
            em.remove(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> findByCourseId(int courseId) {
        return List.of(); // implement later
    }
}
