package br.com.ponteshelison.medicalconsult.user.domain;

import br.com.ponteshelison.medicalconsult.consult.domain.Consult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.ponteshelison.medicalconsult.user.Enum.Permission;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private  Long userId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "PHONE")
    private String phone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "PERMISSION")
    @Enumerated(EnumType.STRING)
    private Permission permission;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Consult> consults;

    public User() {}

    public User(Long userId, String username, String email, String cpf, String phone, Date birthday, Permission permission) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.birthday = birthday;
        this.permission = permission;
    }
}
