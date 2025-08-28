package app.daos;

import app.entities.Course;
import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDAO {
    private final EntityManagerFactory emf;

    public CourseDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Course createCourse(Course course) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Course findById(int id) {
        try (EntityManager em = getEntityManager()) {
            return em.find(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> findAll() {
        try (EntityManager em = getEntityManager()) {
            return em.createQuery("SELECT c FROM Course c", Course.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Course update(Course course) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Course updatedCourse = em.merge(course);
            em.getTransaction().commit();
            return updatedCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(int id) {
        try (EntityManager em = getEntityManager()) {
            em.getTransaction().begin();
            Course course = em.find(Course.class, id);
            if (course == null) {
                System.out.println("Course with ID " + id + " not found.");
                em.getTransaction().rollback();
                return false;
            }
            em.remove(course);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Course> findCoursesByStudentId(int studentId) {
        try (EntityManager em = getEntityManager()) {
            Student student = em.find(Student.class, studentId);
            if (student == null || student.getCourseIds() == null || student.getCourseIds().isEmpty()) {
                return List.of();
            }
            return em.createQuery(
                    "SELECT c FROM Course c WHERE c.id IN :ids", Course.class)
                    .setParameter("ids", student.getCourseIds())
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


}
