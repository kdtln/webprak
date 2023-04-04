package ru.msu.cmc.webprak.models;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name = "deal")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Deal implements CommonEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_deal")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    @ToString.Exclude
    @NonNull
    private Clients client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker")
    @ToString.Exclude
    @NonNull
    private Workers worker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service")
    @ToString.Exclude
    @NonNull
    private Services service;

    @Column(name = "start_date")
    private Date start;

    @Column(name = "end_date")
    private Date end;

    @Column(name = "description")
    private String descr;
}
