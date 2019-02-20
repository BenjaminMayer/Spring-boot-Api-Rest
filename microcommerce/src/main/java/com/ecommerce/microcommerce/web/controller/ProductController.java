package com.ecommerce.microcommerce.web.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.model.Product;




@RestController
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value="/Produits", method=RequestMethod.GET)
    public List<Product> listeProduits() {
        return productDao.findAll();
    }
	
	@RequestMapping(value="/Produits/{id}", method=RequestMethod.GET)
	public Product returnProduct(@PathVariable int id) {
		
		return productDao.findById(id);
	}
	//ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity ajouterProduit(@RequestBody Product product) {
         productDao.save(product);
         
         Product productAdded =  productDao.save(product);

         if (productAdded == null)
             return ResponseEntity.noContent().build();

         URI location = ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(productAdded.getId())
                 .toUri();

         return ResponseEntity.created(location).build();
         
         
    }

}