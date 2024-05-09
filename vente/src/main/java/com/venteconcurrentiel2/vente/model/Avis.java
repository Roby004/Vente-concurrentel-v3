package com.venteconcurrentiel2.vente.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avis {
    @EmbeddedId
    private AvisId id;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    private String commentaire;

    private Float vote;

    @Temporal(TemporalType.DATE)
    private Date dateAvis;

    @ManyToOne
    @JoinColumn(name = "idPro", insertable=false, updatable=false)
    private Produit avisPro;

    @ManyToOne
    @JoinColumn(name = "idCli", insertable=false, updatable=false)
    private Client avisCli;
=======
public class Avis {
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
