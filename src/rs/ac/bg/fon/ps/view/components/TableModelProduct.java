/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.components;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Cartman
 */
public class TableModelProduct extends AbstractTableModel{

    private List<Product> products;
    private String[] columnNames=new String[]{"ID", "Name", "Description", "Price", "Unit","Manufacturer"};
    private Class[] columnClass=new Class[]{Long.class, String.class, String.class, BigDecimal.class, MeasurementUnit.class, Manufacturer.class};
    
    public TableModelProduct(List<Product> products){
        this.products=products;
    }
    
    @Override
    public int getRowCount() {
        if(products==null){
            return 0;
        }else{
            return products.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length){
            return "n/a";
        }else{
            return columnNames[column];
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex>columnClass.length){
            return Object.class;
        }else{
            return columnClass[columnIndex];
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product=products.get(rowIndex);
        switch(columnIndex){
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2: 
                return product.getDescription();
            case 3: 
                return product.getPrice();
            case 4: 
                return product.getMeasurementUnit();
            case 5: 
                return product.getManufacturer();
            default:
                    return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product product=products.get(rowIndex);
        switch(columnIndex){
            case 0:
                product.setId((Long)aValue);
                break;
            case 1: 
                product.setName(aValue.toString());
                break;
            case 2:
                product.setDescription(aValue.toString());
                break;
            case 3:
                product.setPrice(new BigDecimal(aValue.toString()));
                break;
            case 4:
                product.setMeasurementUnit((MeasurementUnit)aValue);
                break;
            case 5:
                product.setManufacturer((Manufacturer)aValue);
                break;
        }
    }
    
    public void addProduct(Product product){
        this.products.add(product);
        //fireTableDataChanged();
        fireTableRowsInserted(products.size()-1, products.size()-1);
    }
    
    public void removeProduct(int row){
        products.remove(row);
        fireTableDataChanged();
    }
    
    public List<Product> getProducts(){
        return products;
    }
    
}
