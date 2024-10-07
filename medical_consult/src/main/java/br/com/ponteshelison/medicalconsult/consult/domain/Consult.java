package br.com.ponteshelison.medicalconsult.consult.domain;

import br.com.ponteshelison.medicalconsult.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "CONSULTS")
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSULT_ID")
    private Long consultId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "CONSULT_DATE")
    private String consultDate;
    @Column(name = "PROFESSIONAL")
    private String professional;
    @Column(name = "SPECIALTY")
    private String specialty;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Consult(){}

    public Consult(Long consultId, String consultDate, String professional, String specialty, User user) {
        this.consultId = consultId;
        this.consultDate = consultDate;
        this.professional = professional;
        this.specialty = specialty;
        this.user = user;
    }
}
