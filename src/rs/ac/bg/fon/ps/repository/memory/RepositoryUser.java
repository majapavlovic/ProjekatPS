/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Milos
 */
public class RepositoryUser {

    private List<User> users;

    public RepositoryUser() {
        this.users = new ArrayList<>();
        this.users.add(new User(1l, "Admin", "Admin", "admin", "admin"));
    }

    public List<User> getAll() {
        return users;
    }

}
