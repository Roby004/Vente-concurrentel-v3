package com.venteconcurrentiel2.vente.service;

<<<<<<< HEAD
import com.venteconcurrentiel2.vente.dto.ProduitPutDto;
import com.venteconcurrentiel2.vente.exception.ProduitNotFoundException;
import com.venteconcurrentiel2.vente.exception.FournisseurNotFoundException;
import com.venteconcurrentiel2.vente.model.Fournisseur;
import com.venteconcurrentiel2.vente.model.Produit;
import com.venteconcurrentiel2.vente.repository.FournisseurRepository;
=======
import com.venteconcurrentiel2.vente.exception.ProduitAlreadyExistsException;
import com.venteconcurrentiel2.vente.exception.ProduitNotFoundException;
import com.venteconcurrentiel2.vente.model.Produit;
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
import com.venteconcurrentiel2.vente.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduitImpService implements ProduitService{
    private final ProduitRepository produitRepository;
<<<<<<< HEAD
    private final FournisseurRepository fournisseurRepository;
=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
    @Override
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

<<<<<<< HEAD
    public List<Produit> getProduitsByFournisseur(Long idFr) {
        List<Produit> produits = produitRepository.findByFournissIdFr(idFr);
       /* for (Produit produit : produits) {
            String base64Image =Base64.getEncoder().encodeToString(produit.getImgPro());
            produit.setImgPro(base64Image);
        }*/
        return produits;
    }
    @Override
    public List<Produit> searchProduits(String query,Long idFr) {
        if (query == null || query.isEmpty()) {
            return produitRepository.findByFournissIdFr(idFr); // Return all produits if no query is provided
        } else {
            // Perform search based on design or categorie
            return produitRepository.findByFournissIdFrAndDesignContainingIgnoreCaseOrCategorieContainingIgnoreCase(idFr,query, query);
        }
    }

    // SELECTIONNER ET RECUPERER UN RPODUIT
   /* public List<Produit> selectUnProduit(int idProduit) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        ConnectionDB connectionDB = new ConnectionDB();
        Connection connection = connectionDB.connect();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM produit WHERE idPro = ?");
        ) {

            statement.setInt(1, idProduit);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("idPro");
                String designation = resultSet.getString("design");
                int prix = resultSet.getInt("prix");
                String description = resultSet.getString("descri");
                int quantite = resultSet.getInt("qte");
                String categorie = resultSet.getString("categorie");
                int nbClic = resultSet.getInt("nbClic");
                //Blob imgPro=resultSet.getBlob("imgPro");
                byte[] imgPro=resultSet.getBytes("imgPro");
                int idFournisseur = resultSet.getInt("idFr");
                String dateAjout = resultSet.getString("dateAjout");

                produits.add(new Produit(id, designation, prix, description, quantite, categorie, nbClic, imgPro, idFournisseur, dateAjout));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return produits;
    }*/

    @Override
    public Produit ajoutProduit(Produit produit, MultipartFile file, Long idFr) throws IOException {

        // Set the supplier by fetching it from the database using supplierId
        Fournisseur fournisseur = fournisseurRepository.findById(idFr)
                .orElseThrow(() -> new FournisseurNotFoundException("Fournisseur avec l'ID " + idFr + " non trouvé"));
        produit.setFourniss(fournisseur);

        try {
            produit.setImgPro(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        }
        return produitRepository.save(produit);
        /*
         /* if (produitExist(produit.getIdPro())) {
            throw new ProduitAlreadyExistsException("Le produit " + produit.getDesign() + " est deja enregistre");
        }
        try {
            produit.setImgPro(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        }
        return produitRepository.save(produit);*/
    }



    public Produit modifProduit(ProduitPutDto produitDto, Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit avec pour ID " + id + " non trouve."));

        // Update the fields of the existing product
        produit.setCategorie(produitDto.getCategorie());
        produit.setDescri(produitDto.getDescri());
        produit.setDesign(produitDto.getDesign());
        produit.setPrix(produitDto.getPrix());
        produit.setQte(produitDto.getQte());

        // Save the updated product
        //Produit updatedProduit = produitRepository.save(produit);

        // Convert the updated product to DTO and return
=======
    @Override
    public Produit ajoutProduit(Produit produit, MultipartFile imgPro) {
        if (produitExist(produit.getIdPro())) {
            throw new ProduitAlreadyExistsException("Le produit " + produit.getDesign() + " est deja enregistre");
        }
        try {
            produit.setImgPro(imgPro.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        }
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
        return produitRepository.save(produit);
    }

    @Override
<<<<<<< HEAD
    public Produit modifProduitImg(MultipartFile imgFile, Long id) throws IOException  {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit avec pour ID " + id + " non trouvé."));

        try {
            // Convert and update imgPro
            existingProduit.setImgPro(imgFile.getBytes());

        } catch (IOException e) {
            throw new RuntimeException("Failed to process image", e);
        }

        return produitRepository.save(existingProduit);
    }

    @Override
    public List<Produit> selectProduitIdAsc(Long id) {
        return List.of();
    }

    @Override
    public List<Produit> filtre2Date(String date1, String date2, Long idFr) {
        return List.of();
    }

    @Override
    public List<Produit> filtre2Prix(int prix1, int prix2, Long idFr) {
        return List.of();
    }

    @Override
    public List<Produit> selectProduitDesignDesc(Long idFr) {
        return List.of();
    }

    @Override
    public List<Produit> selectProduitDesignAsc(Long idFr) {
        return List.of();
    }

    @Override
    public List<Produit> selectProduitIdDesc(Long idFr) {
        return List.of();
    }

    @Override
    public Produit selectUnProduit(Long id) {
=======
    public Produit modifProduit(Produit produit, Long id) {
        Optional<Produit> existProduit = produitRepository.findById(id);

        // si id n'existe pas
        if (!existProduit.isPresent()) {
            throw new ProduitNotFoundException("Produit avec pour ID " + id + " non trouve.");
        }

        Produit pr = existProduit.get();
        pr.setCat(produit.getCat());
        pr.setDateAjout(produit.getDateAjout());
        pr.setDescr(produit.getDescr());
        pr.setDesign(produit.getDesign());
        pr.setImgPro(produit.getImgPro());
        pr.setNbClic(produit.getNbClic());
        pr.setPrix(produit.getPrix());
        pr.setQte(produit.getQte());

        // Save the updated product
        return produitRepository.save(pr);
    }

    @Override
    public Produit getProduitById(Long id) {
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
        return produitRepository.findById(id).orElseThrow(() -> new ProduitNotFoundException("Desole, le produit est introuvable"));
    }

    @Override
    public void suppProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new ProduitNotFoundException("Produit avec pour ID " + id + " non trouve.");
        }
<<<<<<< HEAD
        produitRepository.deleteById(id);

    }



=======

    }
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
    private boolean produitExist(Long idPro) {

        return produitRepository.findById(idPro).isPresent();
    }
<<<<<<< HEAD



    public Optional<Produit> getProduitId(Long id){
        return produitRepository.findById(id);
    }


=======
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
}
