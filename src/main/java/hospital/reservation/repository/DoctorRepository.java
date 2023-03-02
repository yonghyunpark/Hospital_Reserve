package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {

    private final EntityManager em;

    public Long save(Doctor doctor) {
        em.persist(doctor);
        return doctor.getId();
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findByName(String name){
        return em.createQuery("select d from Doctor d where d.name = :name", Doctor.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Doctor> findAll(){
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }
}
