package hospital.reservation.repository;

import hospital.reservation.domain.Reserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class ReserveRepository {

    private final EntityManager em;

    public void save(Reserve reserve){
        em.persist(reserve);
    }

    public Reserve findOne(Long id){
        return em.find(Reserve.class, id);
    }
}
