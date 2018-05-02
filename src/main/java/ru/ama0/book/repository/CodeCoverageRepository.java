package ru.ama0.book.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.entity.CodeCoverage;
import ru.ama0.book.entity.CodeCoverageId;

@Transactional
public interface CodeCoverageRepository extends JpaRepository<CodeCoverage, CodeCoverageId> {

    List<CodeCoverage> findByIdJobIdAndIdNodeIdAndElementType(Integer jobId, Integer nodeId,
                                                              CodeCoverage.ElementType elementType);

    List<CodeCoverage> findByIdJobIdAndParentNodeAndElementTypeIn(Integer jobId, Integer parentNodeId,
                                                                CodeCoverage.ElementType ... elementTypes);

    List<CodeCoverage> findByIdAndElementType(CodeCoverageId id, CodeCoverage.ElementType elementType);
}
