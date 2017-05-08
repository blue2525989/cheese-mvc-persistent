package org.launchcode.models.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.launchcode.models.*;

@Repository
@Transactional
public interface CatagoryDao extends CrudRepository<Catagory, Integer>{

}
