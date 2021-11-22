/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.sql.SQLException;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.db.RepositoryManufacturer;
import rs.ac.bg.fon.ps.repository.db.RepositoryProduct;
import rs.ac.bg.fon.ps.repository.db.RepositoryUser;

/**
 *
 * @author Milos
 */
public class Controller {

    private static Controller instance;
    private final RepositoryUser storageUser;
    private final RepositoryManufacturer storageManufacturer;
    private final RepositoryProduct storageProduct;

    private User currentUser;

    private Controller() {
        this.storageUser = new RepositoryUser();
        this.storageManufacturer = new RepositoryManufacturer();
        this.storageProduct = new RepositoryProduct();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {
        try{
            storageUser.connect();
            List<User> users = storageUser.getAll();
            storageUser.commit();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            throw new Exception("Unknown user!");
        }catch(Exception e){
            storageUser.rollback();
            throw e;
        }finally{
            storageUser.disconnect();
        }
    }

    public List<Manufacturer> getAllManufacturers() throws SQLException {
        return storageManufacturer.getAll();
    }

    public void addProduct(Product product) throws Exception {
        
        storageProduct.connect();
        try{
            if (!storageProduct.getAll().contains(product)) {
                storageProduct.add(product);
                storageProduct.commit();
            } else {
                throw new Exception("Product already exists!");
            }
        }catch(Exception e){
            storageProduct.rollback();
        }finally{
            storageProduct.disconnect();
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        return storageProduct.getAll();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addAllProducts(List<Product> products) throws Exception {
        storageProduct.connect();
        try{
            
                storageProduct.addAll(products);
                storageProduct.commit();
        }catch(Exception e){
            storageProduct.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            storageProduct.disconnect();
        }
    }
}
