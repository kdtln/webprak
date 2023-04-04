package ru.msu.cmc.webprak.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;
@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Services implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_service")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "price")
    @NonNull
    private Long price;

    @Column(name = "describe")
    private String descr;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Services other = (Services) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && Objects.equals(price, other.price)
                && descr.equals(other.descr);
    }

}
