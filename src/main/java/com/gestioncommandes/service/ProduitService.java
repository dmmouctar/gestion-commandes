package com.gestioncommandes.service;

import com.gestioncommandes.dto.ProduitDTO;
import com.gestioncommandes.dto.ProduitRequestDTO;
import com.gestioncommandes.entity.Categorie;
import com.gestioncommandes.entity.Produit;
import com.gestioncommandes.exception.RessourceIntrouvableException;
import com.gestioncommandes.repository.CategorieRepository;
import com.gestioncommandes.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitService(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    public List<Produit> listerProduits() {
        return produitRepository.findAll();
    }

    public Produit trouverParId(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit creerProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit modifierProduit(Long id, ProduitRequestDTO dto) {
        // Rechercher le produit existant
        Produit produitExistant = produitRepository.findById(id)
                .orElse(null);
        if (produitExistant == null) {
            return null;
        }

        // Verifier que la categorie existe
        Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                .orElseThrow(() -> new RessourceIntrouvableException(
                        "Categorie introuvable avec l'identifiant : " + dto.getCategorieId()
                ));

        // Mettre à jour les informations du produit
        produitExistant.setNom(dto.getNom());
        produitExistant.setDescription(dto.getDescription());
        produitExistant.setPrix(dto.getPrix());
        produitExistant.setStock(dto.getStock());
        produitExistant.setCategorie(categorie);

        return produitRepository.save(produitExistant);
    }

    public boolean supprimerProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            return false;
        }
        produitRepository.deleteById(id);
        return true;
    }

    public Produit creerDepuisDTO(ProduitRequestDTO dto) {
        Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                .orElseThrow(() -> new RessourceIntrouvableException(
                        "Categorie introuvable avec l'identifiant : " + dto.getCategorieId()
                ));

        Produit produit = new Produit();

        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        produit.setCategorie(categorie);

        return produitRepository.save(produit);
    }

    public ProduitDTO convertirEnDTO(Produit produit) {
        return new ProduitDTO(
                produit.getId(),
                produit.getNom(),
                produit.getDescription(),
                produit.getPrix(),
                produit.getStock(),
                produit.getCategorie().getId(),
                produit.getCategorie().getNom()
        );
    }
}
