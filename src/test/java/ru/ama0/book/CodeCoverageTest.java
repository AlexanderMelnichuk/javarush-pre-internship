package ru.ama0.book;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.ama0.book.entity.CodeCoverage;
import ru.ama0.book.entity.CodeCoverageId;
import ru.ama0.book.entity.LineCoverage;
import ru.ama0.book.repository.CodeCoverageRepository;
import ru.ama0.book.repository.LineCoverageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaConfiguration.class})
@ActiveProfiles("unittest")
@Transactional
@Rollback
public class CodeCoverageTest {

    @Autowired
    private CodeCoverageRepository codeCoverageRepository;
    @Autowired
    private LineCoverageRepository lineCoverageRepository;

    private CodeCoverage coverageOne;
    private LineCoverage lineCoverageOne;

    @Before
    public void setUp() {
        coverageOne = codeCoverageRepository.save(CodeCoverage.builder()
                .id(new CodeCoverageId(1, 3)).buildId(2).parentNode(null)
                .elementType(CodeCoverage.ElementType.PACKAGE).absoluteName("test.absolute.name")
                .signature("test.signature").relativeName("name").totalClasses(5).coveredClasses(4).totalMethods(15)
                .coveredMethods(12).totalComplexity(55).coveredComplexity(54).totalBranches(1).coveredBranches(1)
                .totalLines(120).coveredLines(98).totalInstructions(111).coveredInstructions(88).sourceId(5)
                .startingLine(1).modifiedCoveredLines(1).modifiedMissedLines(0).build());
        lineCoverageOne = lineCoverageRepository.save(LineCoverage.builder().coveredLines("Covered lines")
                .filename("filename").filepath("filepath").jobId(1).nodeId(3)
                .pvId(5).svnUrl("The svn url").testType(1).totalLines(500).build());
        lineCoverageRepository.save(LineCoverage.builder().coveredLines("Covered lines 2")
                .filename("filename 2").filepath("filepath 2").jobId(2 /*not linked to coverageOne*/).nodeId(3)
                .pvId(5).svnUrl("The svn url 2").testType(2).totalLines(600).build());
    }

    @Test
    public void testFindByIdJobIdAndIdNodeIdAndElementType_finds() throws Exception {
        assertThat(codeCoverageRepository.findByIdJobIdAndIdNodeIdAndElementType(1, 3,
                CodeCoverage.ElementType.PACKAGE),
                is(Collections.singletonList(coverageOne)));
    }

    @Test
    public void testFindByJobIdAndParentNodeAndElementTypesIn_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.findByIdJobIdAndParentNodeAndElementTypeIn(1,
                null, CodeCoverage.ElementType.PACKAGE, CodeCoverage.ElementType.METHOD).size());
    }

    @Test
    public void testFindByIdAndElementTypesIn_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.findByIdAndElementType(new CodeCoverageId(1, 3),
                CodeCoverage.ElementType.PACKAGE).size());
    }

/*
    @Test
    public void testFindFileCoverage_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.findFileCoverage(1).size());
    }
*/

    @Test
    public void testGetFileCoverage_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.getFileCoverage(1).size());
    }

    @Test
    public void testGetSingleFileCoverage_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.getSingleFileCoverage(1, 3).size());
    }
}