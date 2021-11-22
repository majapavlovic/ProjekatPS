/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.math.BigDecimal;
import java.sql.SQLException;
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
public class TestDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseBroker dbbr=new DatabaseBroker();
        dbbr.uspostaviKonekciju();
        
        Manufacturer manufacturer=new Manufacturer(1L, "BIP");
        //Manufacturer manufacturer=new Manufacturer(2L, "Soko Stark");
        //dbbr.kreirajManufacturer(manufacturer);
        
        //List<Manufacturer> manufacturers=dbbr.vratiSveManufacturer();
        //System.out.println(manufacturers);
        //Product product=new Product(1L, "Ruski kvas", "hladno!", new BigDecimal(60), MeasurementUnit.PCS, manufacturer, new User(1L, "Admin", "Admin", "admin", "admin"));
        Product product2=new Product(2L, "Ruski kvas2", "hladno!", new BigDecimal(60), MeasurementUnit.PCS, manufacturer, new User(1L, "Admin", "Admin", "admin", "admin"));
        Product product3=new Product(3L, "Ruski kvas3", "hladno!", new BigDecimal(60), MeasurementUnit.PCS, manufacturer, new User(1L, "Admin", "Admin", "admin", "admin"));
        Product product4=new Product(4L, "Ruski kvas4", "hladno!", new BigDecimal(60), MeasurementUnit.PCS, manufacturer, new User(1L, "Admin", "Admin", "admin", "admin"));
        Product product5=new Product(5L, "Ruski kvas5", "hladno!", new BigDecimal(60), MeasurementUnit.PCS, manufacturer, new User(1L, "Admin", "Admin", "admin", "admin"));
        //dbbr.kreirajProduct(product);
        //dbbr.kreirajProductBoljiNacin(product);
        //System.out.println(dbbr.getAllProduct());
        /*
        User user=new User();
        user.setFirstName("Pera");
        user.setLastName("Peric");
        user.setUsername("perica");
        user.setPassword("perica");
        dbbr.insertUser(user);
        System.out.println("Dodat je User kome je ID="+user.getId());
*/
        List<Product> products=new ArrayList<>();
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        try{
            dbbr.sacuvajListuProizvoda(products);
            dbbr.potvrdiTransakciju();
        }catch(Exception ex){
            dbbr.ponistiTransakciju();
            ex.printStackTrace();
        }
        
        dbbr.raskiniKonekciju();
    }
}
