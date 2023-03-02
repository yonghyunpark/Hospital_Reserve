package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Hospital;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {

    private final EntityManager em;

    public Long save(Hospital hospital){
        em.persist(hospital);
        return hospital.getId();
    }

    public Hospital findOne(Long id){
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findByName(String name){
        return em.createQuery("select h from Hospital h where h.name = :name", Hospital.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Hospital> findAll(){
        return em.createQuery("select h from Hospital h", Hospital.class)
                .getResultList();
    }
}
