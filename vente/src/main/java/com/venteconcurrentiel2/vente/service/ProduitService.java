package com.venteconcurrentiel2.vente.service;
<<<<<<< HEAD
import com.venteconcurrentiel2.vente.dto.ProduitPutDto;
import com.venteconcurrentiel2.vente.model.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit ajoutProduit(Produit produit, MultipartFile imgPro, Long idFr) throws IOException;
    List<Produit> getProduits();
    public List<Produit> getProduitsByFournisseur(Long idFr);
    public Optional<Produit> getProduitId(Long id);
    //public List<Produit> selectUnProduit(int idProduit) throws SQLException ;
    Produit modifProduit(ProduitPutDto produitDto, Long id);
    Produit selectUnProduit(Long id);
    void suppProduit(Long id);

    Produit modifProduitImg(MultipartFile imgFile, Long id) throws IOException ;

    List<Produit> selectProduitIdAsc(Long id);

    List<Produit> filtre2Date(String date1, String date2, Long idFr);

    List<Produit> filtre2Prix(int prix1, int prix2, Long idFr);

    List<Produit> selectProduitDesignDesc(Long idFr);

    List<Produit> selectProduitDesignAsc(Long idFr);

    List<Produit> selectProduitIdDesc(Long idFr);

    List<Produit> searchProduits(String query, Long idFr);
=======
import com.venteconcurrentiel2.vente.model.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProduitService {
    Produit ajoutProduit(Produit produit, MultipartFile imgPro);
    List<Produit> getProduits();
    Produit modifProduit(Produit produit, Long id);
    Produit getProduitById(Long id);
    void suppProduit(Long id);
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
