package com.venteconcurrentiel2.vente.repository;

import com.venteconcurrentiel2.vente.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findByFournissIdFr(Long idFr);
    List<Produit> findByFournissIdFrAndDesignContainingIgnoreCaseOrCategorieContainingIgnoreCase(Long id,String design, String categorie);
=======
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
    //Optional<Produit> findById();

}
