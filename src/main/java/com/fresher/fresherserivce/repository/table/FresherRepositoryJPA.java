package com.fresher.fresherserivce.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fresher.fresherserivce.model.Fresher;

@RequestMapping
public interface FresherRepositoryJPA extends JpaRepository<Fresher, Long> {
}
