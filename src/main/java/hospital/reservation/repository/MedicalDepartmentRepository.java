package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.MedicalDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedicalDepartmentRepository {

    private final EntityManager em;

    public Long save(MedicalDepartment medicalDepartment){
        em.persist(medicalDepartment);
        return medicalDepartment.getId();
    }

    public MedicalDepartment findOne(Long id) {
        return em.find(MedicalDepartment.class, id);
    }

    public List<MedicalDepartment> findByName(String name){
        return em.createQuery("select m from MedicalDepartment m where m.name = :name", MedicalDepartment.class)
                .setParameter("name", name)
                .getResultList();
    }
    public List<MedicalDepartment> findAll(){
        return em.createQuery("select m from MedicalDepartment m", MedicalDepartment.class)
                .getResultList();
    }

}
