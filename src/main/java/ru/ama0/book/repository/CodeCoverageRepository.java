package ru.ama0.book.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.entity.CodeCoverage;

@Transactional
public interface CodeCoverageRepository extends JpaRepository<CodeCoverage, Long> {

    List<CodeCoverage> findByIdJobIdAndIdNodeIdAndElementType(Integer jobId, Integer nodeId,
                                                              CodeCoverage.ElementType elementType);

    List<CodeCoverage> findByIdJobIdAndIdNodeIdAndElementTypeIn(Integer jobId, Integer nodeId,
                                                                CodeCoverage.ElementType ... elementTypes);

    List<CodeCoverage> findByElementTypeIn(List<CodeCoverage.ElementType> elementTypes);
}
