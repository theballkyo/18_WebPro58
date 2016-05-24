/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

/**
 *
 * @author theba
 */
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface SectionDao extends CrudRepository<Section, Integer> {

    public List<Section> findByManager(String username);
    
    public int countByManagerAndSectionId(String username, int sectionId);
}