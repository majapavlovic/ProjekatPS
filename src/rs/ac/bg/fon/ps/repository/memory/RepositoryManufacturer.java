/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;

/**
 *
 * @author Milos
 */
public class RepositoryManufacturer {

    private List<Manufacturer> manufacturers;

    public RepositoryManufacturer() {
        manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer(1l, "Bambi"));
        manufacturers.add(new Manufacturer(2l, "Soko Stark"));
        manufacturers.add(new Manufacturer(3l, "Ravanica"));
    }

    public List<Manufacturer> getAll() {
        return manufacturers;
    }

}
