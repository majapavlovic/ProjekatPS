/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Cartman
 */
public class DatabaseBroker {

    private Connection connection;

    public void uspostaviKonekciju() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/psdbd22001";
                String user = "root";
                String password = "root";
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }

    }

    public void raskiniKonekciju() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Neuspesno raskidanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }
    }
    
    public void potvrdiTransakciju() throws SQLException{
        if (connection != null) {
            try {
                connection.commit();
                System.out.println("Uspesno potvrdjena transakcija!");
            } catch (SQLException ex) {
                System.out.println("Neuspesno potvrdjivanje transakcije!\n" + ex.getMessage());
                throw ex;
            }
        }
    }
    
    public void ponistiTransakciju() throws SQLException{
        if (connection != null) {
            try {
                connection.rollback();
                System.out.println("Uspesno ponistena transakcija!");
            } catch (SQLException ex) {
                System.out.println("Neuspesno ponistavanje transakcije!\n" + ex.getMessage());
                throw ex;
            }
        }
    }
    
    public void kreirajManufacturer(Manufacturer manufacturer) throws SQLException{
        try {
            String upit="INSERT INTO Manufacturer (id, name) VALUES ("
                    + ""+manufacturer.getId()+","
                    + "'"+manufacturer.getName()+"'"
                    + ")";
            Statement statement=connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
            System.out.println("Uspesno kreiran Manufacturer!");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje Manufacturer!\n"+ex);
            throw ex;
        }
    }
    
    public List<Manufacturer> vratiSveManufacturer() throws SQLException{
        try {
            List<Manufacturer> manufacturers=new ArrayList<>();
            String upit="SELECT id AS sifra, name FROM Manufacturer";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(upit);
            
            while(rs.next()){
                Manufacturer manufacturer=new Manufacturer();
                manufacturer.setId(rs.getLong("id"));
                manufacturer.setName(rs.getString("sifra"));
                manufacturers.add(manufacturer);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista Manufacturer!");
            return manufacturers;
        } catch (SQLException ex) {
            System.out.println("Neuspesno ucitavanje liste Manufacturrer!\n"+ex);
            throw ex;
        }
    }
    
    public void kreirajProduct(Product product) throws SQLException{
        try {
            String upit="INSERT INTO Product (id, name, description, price, measurementUnit, manufacturer, savedByUser) VALUES ("
                    + ""+product.getId()+", "
                    + "'"+product.getName()+"',"
                    + "'"+product.getDescription()+"',"
                    + ""+product.getPrice()+","
                    + "'"+product.getMeasurementUnit().toString()+"',"
                    + ""+product.getManufacturer().getId()+","
                    + ""+product.getSavedByUser().getId()+""
                    + ")";
            
            Statement statement=connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
            System.out.println("Uspesno kreiranje Product!");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje Product!");
            throw ex;
        }
        
    }
    
    public void kreirajProductBoljiNacin(Product product) throws SQLException{
        try {
            String upit="INSERT INTO Product (id, name, description, price, measurementUnit, manufacturer, savedByUser) VALUES (?,?,?,?,?,?,?)";
            
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
    
    public List<Product> getAllProduct() throws SQLException{
        List<Product> products=new ArrayList<>();
        try{
            String upit="SELECT p.id AS pid, p.name AS pname, p.description AS description, p.price AS price, p.measurementunit AS measurementunit, m.id AS mid, m.name AS mname, u.id as uid, u.firstname AS firstname, u.lastname AS lastname, u.username AS username, u.password AS password FROM Product p INNER JOIN Manufacturer m ON p.manufacturer=m.id INNER JOIN User u ON p.savedbyuser=u.id";
            
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
    
    
    public void insertUser(User user) throws SQLException{
        try {
            String upit="INSERT INTO User (firstname, lastname, username, password) VALUES (?,?,?,?)";
            
            PreparedStatement statement=connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            
            statement.executeUpdate();
            ResultSet rs=statement.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getLong(1));
            }
            statement.close();
            System.out.println("Uspesno kreiranje User!");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje User!");
            throw ex;
        }
    }
    
    public void sacuvajListuProizvoda(List<Product> products) throws SQLException{
        try {
            String upit="INSERT INTO Product (id, name, description, price, measurementUnit, manufacturer, savedByUser) VALUES (?,?,?,?,?,?,?)";
            
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
            throw ex;
        }
        
    }
    
}
