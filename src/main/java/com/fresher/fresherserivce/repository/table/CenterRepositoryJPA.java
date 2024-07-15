package com.fresher.fresherserivce.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresher.fresherserivce.model.Center;

@Repository
public interface CenterRepositoryJPA extends JpaRepository<Center, Long> {
}
