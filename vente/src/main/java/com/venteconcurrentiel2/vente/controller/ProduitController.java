package com.venteconcurrentiel2.vente.controller;
<<<<<<< HEAD
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.venteconcurrentiel2.vente.dto.ProduitDto;
import com.venteconcurrentiel2.vente.dto.ProduitPutDto;
import com.venteconcurrentiel2.vente.exception.ProduitNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
=======
import java.util.List;

import org.springframework.http.HttpStatus;
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venteconcurrentiel2.vente.model.Produit;
import com.venteconcurrentiel2.vente.service.ProduitService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Produits")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService prodService;
<<<<<<< HEAD

    @GetMapping("/getAllProduit")
    public ResponseEntity<List<Produit>> getProduits() {

        return new ResponseEntity<>(prodService.getProduits(), HttpStatus.FOUND);
    }

    @GetMapping("/{idPro}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long idPro) {
        Optional<Produit> produitOptional = prodService.getProduitId(idPro);
        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();
            byte[] imageBytes = Base64.getDecoder().decode(produit.getImgPro());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PostMapping("/addProduit")
    public Produit addProduct(@ModelAttribute Produit produit, @RequestParam("imgFile") MultipartFile imgFile) throws IOException, IOException {
        return prodService.ajoutProduit(produit, imgFile);

    }*/
    @PostMapping("/addProduit/{idFr}")
    public Produit addProduit(@ModelAttribute Produit produit,
                              @RequestParam("imgFile") MultipartFile imgFile,
                              @PathVariable("idFr") Long idFr) throws IOException {
        produit.setDateAjout(new Date());
        System.out.println("Received imgFile: " + imgFile.getOriginalFilename());
        System.out.println("Received idFr: " + idFr);
        return prodService.ajoutProduit(produit, imgFile, idFr);
    }

    /*@PostMapping
    public Produit ajoutProduit(@RequestBody Produit produit, @RequestParam("imgPro") MultipartFile imgPro){
        return prodService.ajoutProduit(produit, imgPro);

    }*/
    @PutMapping("/produitPut/{id}")
    public ResponseEntity<String> modifProduit(@RequestBody ProduitPutDto produitDto, @PathVariable Long id) {
        System.out.println("Received idPro: " + id);
        try {
            Produit updatedProduit = prodService.modifProduit(produitDto, id);
            return ResponseEntity.ok("Produit modifie avec succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur de modification du produit: " + e.getMessage());
        }
    }

    @PutMapping("/produitPutImg/{id}")
    public Produit modifProduitImg(@RequestParam("imgFile") MultipartFile imgFile, @PathVariable Long id) throws IOException {
        System.out.println("Received imgFile: " + imgFile.getOriginalFilename());
        System.out.println("Received idFr: " + id);
        return prodService.modifProduitImg(imgFile, id);
    }

    @DeleteMapping("/produitDelete/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable Long id) {
        try {
            prodService.suppProduit(id);
            return ResponseEntity.ok("Produit a été supprimé avec succès.");
        } catch (ProduitNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit avec l'ID " + id + " non trouvé.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la suppression du produit.");
        }
    }

    @GetMapping("/getUnProduit/{idPro}")
    public ResponseEntity<ProduitDto> getUnProduit(@PathVariable Long idPro) {
        System.out.println("id du produit recupere: " + idPro);
        Produit produit = prodService.selectUnProduit(idPro);
        System.out.println("produit recupere: " + produit);

        if (produit == null) {
            System.out.println("produit null");
            return ResponseEntity.notFound().build();
        } else {
            ProduitDto produitDto = new ProduitDto();
            produitDto.setDesign(produit.getDesign());
            produitDto.setCategorie(produit.getCategorie());
            produitDto.setPrix(produit.getPrix());
            produitDto.setQte(produit.getQte());
            produitDto.setDescri(produit.getDescri());
            produitDto.setDateAjout(produit.getDateAjout());
            produitDto.setImgPro(convertImageToBase64(produit.getImgPro())); // Convert byte[] to base64

            System.out.println("produit recupere: " + produitDto);
            return ResponseEntity.ok(produitDto);
        }
    }

    @GetMapping("/fournisseur/produits/{idFr}")
    public ResponseEntity<List<ProduitDto>> getAllProduitByFournisseur(@PathVariable Long idFr) {
        List<Produit> produits = prodService.getProduitsByFournisseur(idFr);
        List<ProduitDto> produitDtos = new ArrayList<>();
        if (produits.isEmpty()) {
            return ResponseEntity.noContent().build(); // Or return 404 if preferred
        } else {
            /*for (Produit produit : produits) {
                // Fetch and set the image for each product
                produit.setImgPro(getProductImage(produit.getIdPro()));
            }*/

            for (Produit produit : produits) {
                ProduitDto produitDto = new ProduitDto();
                produitDto.setIdPro(produit.getIdPro());
                produitDto.setDesign(produit.getDesign());
                produitDto.setCategorie(produit.getCategorie());
                produitDto.setPrix(produit.getPrix());
                produitDto.setQte(produit.getQte());
                produitDto.setDescri(produit.getDescri());
                produitDto.setDateAjout(produit.getDateAjout());
                produitDto.setImgPro(convertImageToBase64(produit.getImgPro())); // Convert byte[] to base64
                produitDtos.add(produitDto);
            }
            return ResponseEntity.ok(produitDtos);

        }
    }

    @GetMapping("/search/{query}/{idFr}")
    public ResponseEntity<List<ProduitDto>> searchProduitsByIdFr(@PathVariable String query,@PathVariable Long idFr) {

            List<Produit> produits = prodService.searchProduits(query, idFr);
            List<ProduitDto> produitDtos = new ArrayList<>();
            if (produits.isEmpty()) {
                return ResponseEntity.noContent().build(); // Or return 404 if preferred
            } else {
            /*for (Produit produit : produits) {
                // Fetch and set the image for each product
                produit.setImgPro(getProductImage(produit.getIdPro()));
            }*/

                for (Produit produit : produits) {
                    ProduitDto produitDto = new ProduitDto();
                    produitDto.setIdPro(produit.getIdPro());
                    produitDto.setDesign(produit.getDesign());
                    produitDto.setCategorie(produit.getCategorie());
                    produitDto.setPrix(produit.getPrix());
                    produitDto.setQte(produit.getQte());
                    produitDto.setDescri(produit.getDescri());
                    produitDto.setDateAjout(produit.getDateAjout());
                    produitDto.setImgPro(convertImageToBase64(produit.getImgPro())); // Convert byte[] to base64
                    produitDtos.add(produitDto);
                }
                return ResponseEntity.ok(produitDtos);

            }

    }

    private String convertImageToBase64(byte[] imgPro) {
        if (imgPro != null && imgPro.length > 0) {
            return Base64.getEncoder().encodeToString(imgPro);
        }
        return null;
    }

    /*
    *************************************
        FILTRE DES PRODUITS
    ************************************
     */
    @GetMapping(value = "/produitsIdAsc/{idFr}")
    public List<Produit> getProduitIdAsc(@PathVariable Long idFr) {
        List<Produit> produits = prodService.selectProduitIdAsc(idFr);
        return produits;
    }

    @GetMapping(value = "/produitsIdDesc/{idFr}")
    public List<Produit> getProduitIdDesc(@PathVariable Long idFr) {
        List<Produit> produits = prodService.selectProduitIdDesc(idFr);
        return produits;
    }

    @GetMapping(value = "/produitsDesignAsc/{idFr}")
    public List<Produit> getProduitDesignAsc(@PathVariable Long idFr) {
        List<Produit> produits = prodService.selectProduitDesignAsc(idFr);
        return produits;
    }

    @GetMapping(value = "/produitsDesignDesc/{idFr}")
    public List<Produit> getProduitDesignDesc(@PathVariable Long idFr) {
        List<Produit> produits = prodService.selectProduitDesignDesc(idFr);
        return produits;
    }

    @GetMapping(value = "/produitsFiltre2Prix/{prix1}/{prix2}/{idFr}")
    public List<Produit> getProduitBtwPrix(@PathVariable int prix1, @PathVariable int prix2,@PathVariable Long idFr) {
        List<Produit> produits = prodService.filtre2Prix(prix1, prix2,idFr);
        return produits;
    }
    @GetMapping(value="/produitsFiltre2Dates/{date1}/{date2}/{idFr}")
    public  List<Produit> getProduitBtwDate(@PathVariable String date1, @PathVariable String date2,@PathVariable Long idFr){
        List<Produit> produits=prodService.filtre2Date(date1,date2,idFr);
        return produits;
    }
}

=======
    @GetMapping
    public ResponseEntity<List<Produit>> getProduits(){

        return new ResponseEntity<> (prodService.getProduits(),HttpStatus.FOUND);
    }

    @PostMapping
    public Produit ajoutProduit(@RequestBody Produit produit, @RequestParam("imgPro") MultipartFile imgPro){
        return prodService.ajoutProduit(produit, imgPro);

    }

    @PutMapping("/Modifier/{id}")
    public Produit modifProduit(@RequestBody Produit produit, @PathVariable Long id){
        return prodService.modifProduit(produit, id);
    }

    @DeleteMapping("/Supprimer/{id}")
    public void suppProduit(@PathVariable Long id){
        prodService.suppProduit(id);

    }

    public Produit getProduitById(@PathVariable Long id){
        return prodService.getProduitById(id);
    }

}
>>>>>>> 0e7fcddd791e040f2e040ce47ed462ef3b2b7dab
