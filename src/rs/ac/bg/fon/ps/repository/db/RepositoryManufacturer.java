/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;

/**
 *
 * @author Cartman
 */
public class RepositoryManufacturer extends DbRepository{

    //private List<Manufacturer> manufacturers;
    
    private Connection connection;

    public RepositoryManufacturer() {
        /*manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer(1l, "Bambi"));
        manufacturers.add(new Manufacturer(2l, "Soko Stark"));
        manufacturers.add(new Manufacturer(3l, "Ravanica"));*/
    }

    public List<Manufacturer> getAll() throws SQLException {
        try {
            List<Manufacturer> manufacturers=new ArrayList<>();
            String upit="SELECT id AS sifra, name FROM Manufacturer";
            connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(upit);
            
            while(rs.next()){
                Manufacturer manufacturer=new Manufacturer();
                manufacturer.setId(rs.getLong("sifra"));
                manufacturer.setName(rs.getString("name"));
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

}
