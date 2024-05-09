package com.venteconcurrentiel2.vente.service;

import com.venteconcurrentiel2.vente.dto.FournisseurDto;
import com.venteconcurrentiel2.vente.model.Fournisseur;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
public interface FournisseurService {
    Fournisseur enregistrer(FournisseurDto fournisseurDto);
    Fournisseur findByMail(String mail);
    Fournisseur validateFournisseur(String mail, String password);
    Fournisseur getFournisseurById(Long idFr);
<<<<<<< HEAD

    List<Fournisseur> selectFournisseurs();

    List<Fournisseur> selectFournisseursSortedByIdAsc();

    List<Fournisseur> selectAllFournisseursSortedByIdDesc();

    List<Fournisseur> selectFournisseursSortedByNomAsc();

    List<Fournisseur> selectFournisseursSortedByNomDesc();

    List<Fournisseur> searchFournisseurs(String recherche);

    int countFournisseurs();

    Fournisseur updateFournisseur(Fournisseur fournisseur);
=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
