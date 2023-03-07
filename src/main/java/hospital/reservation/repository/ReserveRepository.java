package hospital.reservation.repository;

import hospital.reservation.domain.Reserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


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

    public List<Reserve> findAll(){
        return em.createQuery("select r from Reserve r", Reserve.class)
                .getResultList();
    }
}
