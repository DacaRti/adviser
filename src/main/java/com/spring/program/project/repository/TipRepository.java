package com.spring.program.project.repository;

import com.spring.program.project.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author DacaP
 * @version 1.0
 */
@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {

}
