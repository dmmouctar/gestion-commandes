package com.gestioncommandes.dto;

import java.math.BigDecimal;

public class ProduitDTO {

    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private Integer stock;
    private Long categorieId;
    private String categorieNom;

    public ProduitDTO() {
    }

    public ProduitDTO(
            Long id,
            String nom,
            String description,
            BigDecimal prix,
            Integer stock,
            Long categorieId,
            String categorieNom) {

        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.categorieId = categorieId;
        this.categorieNom = categorieNom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }
}