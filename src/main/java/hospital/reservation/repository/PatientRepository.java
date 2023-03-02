package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepository {

    private final EntityManager em;

    public Long save(Patient patient){
        em.persist(patient);
        return patient.getId();
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findByName(String name){
        return em.createQuery("select p from Patient p where p.name = :name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Patient> findAll(){
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }

}
