package ru.ama0.book.repository;

import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.dto.FileCoverage;
import ru.ama0.book.entity.CodeCoverage;
import ru.ama0.book.entity.CodeCoverageId;

@Transactional
public interface CodeCoverageRepository extends JpaRepository<CodeCoverage, CodeCoverageId> {

    List<CodeCoverage> findByIdJobIdAndIdNodeIdAndElementType(Integer jobId, Integer nodeId,
                                                              CodeCoverage.ElementType elementType);

    List<CodeCoverage> findByIdJobIdAndParentNodeAndElementTypeIn(Integer jobId, Integer parentNodeId,
                                                                  CodeCoverage.ElementType... elementTypes);

    List<CodeCoverage> findByIdAndElementType(CodeCoverageId id, CodeCoverage.ElementType elementType);

    String FILE_COVERAGE_QUERY = "SELECT TRIM(l.svn_url) as svnUrl,"
            + " TRIM(l.filepath) as absolutePath,"
            + " TRIM(l.filename) as name,"
            + " l.covered_lines as lineCoveredLines,"
            + " c.total_classes as classes,"
            + " c.covered_classes as classesCovered,"
            + " c.total_methods as methods,"
            + " c.covered_methods as methodsCovered,"
            + " c.total_complexity as complexity,"
            + " c.covered_complexity as complexityCovered,"
            + " c.total_branches as branches,"
            + " c.covered_branches as branchesCovered,"
            + " c.total_lines as \"lines\"," // 'lines' is a keyword in MYSQL, and H2 requires double quotes
            + " c.covered_lines as linesCovered,"
            + " c.total_instructions as instructions,"
            + " c.covered_instructions as instructionsCovered,"
            + " c.node_id as id"
            + " FROM coverage_code_coverage c "
            + " INNER JOIN coverage_line_coverage l on "
            + " c.job_id = l.job_id and c.node_id = l.node_id WHERE c.job_id = :jobId";

    @Query(nativeQuery = true, value = FILE_COVERAGE_QUERY + " ORDER BY TRIM(l.svn_url)")
    List<FileCoverage> findFileCoverage(@NonNull @Param("jobId") Integer jobId);

    @Query(nativeQuery = true, value = FILE_COVERAGE_QUERY + " and c.node_id = :fileId")
    List<FileCoverage> findFileCoverage(@NonNull @Param("jobId") Integer jobId, @NonNull @Param("fileId") Integer fileId);

    String FILE_COVERAGE_JPQL_QUERY = "SELECT NEW ru.ama0.book.dto.FileCoverage(c, l)"
            + " FROM CodeCoverage c, LineCoverage l"
            + " WHERE c.id.jobId = :jobId"
            + " AND l.jobId = :jobId"
            + " AND c.id.nodeId = l.nodeId";

    @Query(FILE_COVERAGE_JPQL_QUERY + " ORDER BY l.svnUrl")
    List<FileCoverage> getFileCoverage(@NonNull @Param("jobId") Integer jobId);

    @Query(FILE_COVERAGE_JPQL_QUERY + " AND c.id.nodeId = :fileId")
    List<FileCoverage> getSingleFileCoverage(@NonNull @Param("jobId") Integer jobId,
                                             @NonNull @Param("fileId") Integer fileId);
}
