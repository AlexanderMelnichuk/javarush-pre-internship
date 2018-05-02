package ru.ama0.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ama0.book.entity.LineCoverage;

public interface LineCoverageRepository extends JpaRepository<LineCoverage, Integer> {

}
