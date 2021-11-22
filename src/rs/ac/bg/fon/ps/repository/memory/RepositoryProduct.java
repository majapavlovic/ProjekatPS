/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Milos
 */
public class RepositoryProduct {

    private List<Product> products;

    public RepositoryProduct() {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> getAll() {
        return products;
    }
}
