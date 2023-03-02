package hospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    private String name;
    private Long career;

    @OneToMany(mappedBy = "doctor")
    private List<Reserve> reserves = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalDepartment_id")
    private MedicalDepartment medicalDepartment;

    //==연관관계 메서드==//
    public void setMedicalDepartment(MedicalDepartment medicalDepartment){
        this.medicalDepartment = medicalDepartment;
        medicalDepartment.getDoctors().add(this);
    }
}
