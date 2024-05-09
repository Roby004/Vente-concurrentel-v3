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
public class Achat {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @EmbeddedId
    private AchatId id;

    @ManyToOne
    @JoinColumn(name = "idCli",insertable=false, updatable=false)
    private Client achatCli;

    @ManyToOne
    @JoinColumn(name = "idPro",insertable=false, updatable=false)
    private Produit achatPro;

    @Column(name = "qteAchat")
    private int qteAchat;

    @Temporal(TemporalType.TIMESTAMP)

    private Date dateAchat;
=======
public class Achat {
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
