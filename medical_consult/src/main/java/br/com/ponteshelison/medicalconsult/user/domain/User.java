package br.com.ponteshelison.medicalconsult.user.domain;

import br.com.ponteshelison.medicalconsult.user.Enum.Permission;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "PERMISSION")
    private Permission permission;

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
