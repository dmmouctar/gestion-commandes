package com.gestioncommandes.controller;

import com.gestioncommandes.dto.ProduitDTO;
import com.gestioncommandes.dto.ProduitRequestDTO;
import com.gestioncommandes.entity.Produit;
import com.gestioncommandes.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<ProduitDTO> listerProduits() {
        return produitService.listerProduits()
                .stream()
                .map(produitService::convertirEnDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProduitDTO trouverParId(@PathVariable Long id) {
        Produit produit = produitService.trouverParId(id);
        if (produit == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produit introuvable avec l'identifiant : " + id
            );
        }
        return produitService.convertirEnDTO(produit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProduitDTO creerProduit(
            @Valid @RequestBody ProduitRequestDTO dto) {
        Produit produit = produitService.creerDepuisDTO(dto);
        return produitService.convertirEnDTO(produit);
    }

    @PutMapping("/{id}")
    public ProduitDTO modifierProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitRequestDTO dto) {
        Produit produit = produitService.modifierProduit(id, dto);
        if (produit == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produit introuvable avec l'identifiant : " + id
            );
        }

        return produitService.convertirEnDTO(produit);
    }

    @DeleteMapping("/{id}")
    public boolean supprimerProduit(@PathVariable Long id) {
        return produitService.supprimerProduit(id);
    }
}