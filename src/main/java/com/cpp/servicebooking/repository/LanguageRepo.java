package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
    Language findByName(String name);
}
