/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Cartman
 */
public class RepositoryProduct extends DbRepository {

    //private List<Product> products;
    private Connection connection;

    public RepositoryProduct() {
        //products = new ArrayList<>();
        
    }

    public void add(Product product) throws SQLException {
        try {
            String upit="INSERT INTO Product (id, name, description, price, measurementUnit, manufacturer, savedByUser) VALUES (?,?,?,?,?,?,?)";
            connection=DbConnectionFactory.getInstance().getConnection();
            PreparedStatement statement=connection.prepareStatement(upit);
            
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getPrice());
            statement.setString(5, product.getMeasurementUnit().toString());
            statement.setLong(6, product.getManufacturer().getId());
            statement.setLong(7, product.getSavedByUser().getId());
            
            statement.executeUpdate();
            statement.close();
            System.out.println("Uspesno kreiranje Product!");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje Product!");
            throw ex;
        }
    }

    public List<Product> getAll() throws SQLException {
        List<Product> products=new ArrayList<>();
        try{
            String upit="SELECT p.id AS pid, p.name AS pname, p.description AS description, p.price AS price, p.measurementunit AS measurementunit, m.id AS mid, m.name AS mname, u.id as uid, u.firstname AS firstname, u.lastname AS lastname, u.username AS username, u.password AS password FROM Product p INNER JOIN Manufacturer m ON p.manufacturer=m.id INNER JOIN User u ON p.savedbyuser=u.id";
            connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(upit);
            while(rs.next()){
                Product product=new Product();
                product.setId(rs.getLong("pid"));
                product.setName(rs.getString("pname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setMeasurementUnit(MeasurementUnit.valueOf(rs.getString("measurementunit")));
                Manufacturer manufacturer=new Manufacturer();
                manufacturer.setId(rs.getLong("mid"));
                manufacturer.setName(rs.getString("mname"));
                product.setManufacturer(manufacturer);
                User user=new User();
                user.setId(rs.getLong("uid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                product.setSavedByUser(user);
                products.add(product);
                
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje liste Product!");
        }catch(SQLException ex){
            System.out.println("Neuspesno ucitavanje liste Product!");
            throw ex;
        }
        
        
        return products;
    }

    public void addAll(List<Product> products) throws SQLException {
        try {
            String upit="INSERT INTO Product (id, name, description, price, measurementUnit, manufacturer, savedByUser) VALUES (?,?,?,?,?,?,?)";
            connection=DbConnectionFactory.getInstance().getConnection();
            PreparedStatement statement=connection.prepareStatement(upit);
            
            for (Product product : products) {
                statement.setLong(1, product.getId());
                statement.setString(2, product.getName());
                statement.setString(3, product.getDescription());
                statement.setBigDecimal(4, product.getPrice());
                statement.setString(5, product.getMeasurementUnit().toString());
                statement.setLong(6, product.getManufacturer().getId());
                statement.setLong(7, product.getSavedByUser().getId());

                statement.executeUpdate();
            }
            statement.close();
            System.out.println("Uspesno kreiranje liste Product!");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje liste Product!");
            ex.printStackTrace();
            throw ex;
        }
    }
}
