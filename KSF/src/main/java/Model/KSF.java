/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author Admin
 */
public abstract class KSF <EntityType,KeyType> {
    public abstract void insert(EntityType Entity);
    public abstract void update(EntityType Entity);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectbyID(KeyType id);
    public abstract List<EntityType> selectbyID(String sql, Object...agrs);
    
    
}
