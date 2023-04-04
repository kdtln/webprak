package ru.msu.cmc.webprak.models;


import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;
@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Clients implements CommonEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_client")
    private Long id;
    @Column(name = "company_name")
    private String name;

    @Column(nullable = false, name = "face")
    @NonNull
    private String face;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Column(nullable = false, name = "login")
    @NonNull
    private String login;

    @Column(nullable = false, name = "password")
    @NonNull
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clients other = (Clients) o;

        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && face.equals(other.face)
                && email.equals(other.email)
                && login.equals(other.login)
                && password.equals(other.password);
    }

}

