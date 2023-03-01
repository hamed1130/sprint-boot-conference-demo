package com.mycompany.conferencedemo.repositories;

import com.mycompany.conferencedemo.models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
