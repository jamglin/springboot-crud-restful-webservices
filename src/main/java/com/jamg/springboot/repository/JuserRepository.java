package com.jamg.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jamg.springboot.entity.Juser;

@Repository
public interface JuserRepository extends JpaRepository<Juser, Long>{

}
