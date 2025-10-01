package com.setec.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setec.Entities.Booked;

public interface BookedRepos extends JpaRepository<Booked, Integer>{

}
