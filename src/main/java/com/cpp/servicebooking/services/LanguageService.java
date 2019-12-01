package com.cpp.servicebooking.services;

import com.cpp.servicebooking.Request.AdminRequest.LanguageRequest;
import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.models.Language;
import com.cpp.servicebooking.repository.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepo languageRepo;

    public List<Language> getLanguages() {
        return languageRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Language saveLanguage(LanguageRequest languageRequest) {
        try {
            Language language = new Language();
            language.setName(languageRequest.getName());
            return languageRepo.save(language);
        } catch (Exception e) {
            throw new DuplicateAccountException("Rolename '"+ languageRequest.getName() +"' already exists");
        }
    }
}
