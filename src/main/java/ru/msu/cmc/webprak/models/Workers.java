package ru.msu.cmc.webprak.models;
import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;
@Entity
@Table(name = "workers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Workers implements CommonEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_worker")
    private Long id;

    @Column(nullable = false, name = "full_name")
    @NonNull
    private String name;

    @Column(name = "address")
    private String address;

    @Column(nullable = false, name = "phone")
    @NonNull
    private String phone;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Column(name = "education")
    private String educ;

    @Column(nullable = false, name = "post")
    @NonNull
    private String post;

    @Column(nullable = false, name = "login")
    @NonNull
    private String login;

    @Column(nullable = false, name = "password")
    @NonNull
    private String password;

    @Column(nullable = false, name = "is_admin")
    @NonNull
    private Long is_admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workers other = (Workers) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && address.equals(other.address)
                && phone.equals(other.phone)
                && email.equals(other.email)
                && educ.equals(other.educ)
                && post.equals(other.post)
                && login.equals(other.login)
                && password.equals(other.password)
                && Objects.equals(is_admin, other.is_admin);
    }

}
