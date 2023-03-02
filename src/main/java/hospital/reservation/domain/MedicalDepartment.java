package hospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class MedicalDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_department_id")
    private Long id;

    private String name;
    private String phoneNum;

    @OneToMany(mappedBy = "medicalDepartment", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    //==연관관계 메서드==
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getMedicalDepartments().add(this);
    }
}

