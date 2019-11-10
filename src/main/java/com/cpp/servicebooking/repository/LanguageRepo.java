package com.cpp.servicebooking.repository;

import com.cpp.servicebooking.models.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends CrudRepository<Language, Long> {
    Language findByName(String name);
}
