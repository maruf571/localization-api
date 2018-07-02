package com.maruf.localization.repository;

import com.maruf.localization.entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {
}
