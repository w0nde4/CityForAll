package com.example.accessmap.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Общий репозиторий для всех справочников. @NoRepositoryBean — сам он не
 * становится Spring-бином, а служит базой для конкретных интерфейсов ниже
 * (CurbHeightRepository и т.д.), каждый из которых — просто одна строчка.
 */
@NoRepositoryBean
public interface DictionaryRepository<T extends AbstractDictionaryEntity> extends JpaRepository<T, Long> {
}
