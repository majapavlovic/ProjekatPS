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
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Cartman
 */
public class RepositoryUser extends DbRepository {

    //private List<User> users;
    private Connection connection;

    public RepositoryUser() {
        //this.users = new ArrayList<>();
        //this.users.add(new User(1l, "Admin", "Admin", "admin", "admin"));
    }

    public List<User> getAll() throws SQLException {
        try {
            
            List<User> users=new ArrayList<>();
            String upit="SELECT id, firstname, lastname, username, password FROM User";
            connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(upit);
            
            while(rs.next()){
                User user=new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitana lista User!");
            return users;
        } catch (SQLException ex) {
            System.out.println("Neuspesno ucitavanje liste User!\n"+ex);
            throw ex;
        }
    }

}
