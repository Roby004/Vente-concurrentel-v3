package com.venteconcurrentiel2.vente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPro;
    private String design;

    @Column(nullable = false)
    private Integer prix;

    @Column(nullable = false)
<<<<<<< HEAD
    private String descri;
=======
    private String descr;
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab

    @Column(nullable = false)
    private Integer qte;

    @Lob
<<<<<<< HEAD
    @Column(name = "imgPro", columnDefinition = "LONGBLOB")
    private byte[] imgPro;

    private  String categorie;
    private Integer nbClic = 0;

    @Temporal(TemporalType.DATE)
=======
    private byte[] imgPro;

    private  String cat;
    private Integer nbClic;
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
    private Date dateAjout;

    @ManyToOne
    @JoinColumn(name = "idFr", nullable = false)
    private Fournisseur fourniss;
<<<<<<< HEAD

    @OneToMany(mappedBy = "achatPro")
    private Set<Achat> achats;

    @OneToMany(mappedBy = "avisPro")
    private Set<Avis> avis;
=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
