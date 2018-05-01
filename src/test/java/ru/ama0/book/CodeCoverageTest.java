package ru.ama0.book;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
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
import ru.ama0.book.repository.CodeCoverageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JpaConfiguration.class})
@ActiveProfiles("unittest")
@Transactional
@Rollback
public class CodeCoverageTest {

    @Autowired
    private CodeCoverageRepository codeCoverageRepository;

    private CodeCoverage coverageOne;


    @Before
    public void setUp() {
        coverageOne = codeCoverageRepository.save(CodeCoverage.builder().jobId(1).buildId(2).nodeId(3).parentNode(null)
                .elementType(CodeCoverage.ElementType.PACKAGE).absoluteName("test.absolute.name")
                .signature("test.signature").relativeName("name").totalClasses(5).coveredClasses(4).totalMethods(15)
                .coveredMethods(12).totalComplexity(55).coveredComplexity(54).totalBranches(1).coveredBranches(1)
                .totalLines(120).coveredLines(98).totalInstructions(111).coveredInstructions(88).sourceId(5)
                .startingLine(1).modifiedCoveredLines(1).modifiedMissedLines(0).build());
    }

    @Test
    public void testFindByJobIdAndNodeIdAndElementType_finds() throws Exception {
        assertThat(codeCoverageRepository.findByJobIdAndNodeIdAndElementType(1, 3,
                CodeCoverage.ElementType.PACKAGE),
                is(Collections.singletonList(coverageOne)));
    }

    @Test
    public void testFindByJobIdAndNodeIdAndElementTypesIn_finds() throws Exception {
        assertEquals(1, codeCoverageRepository.findByJobIdAndNodeIdAndElementTypeIn(1, 3,
                CodeCoverage.ElementType.PACKAGE, CodeCoverage.ElementType.METHOD).size());
    }

/*
    @Test
    public void testFindByJobId_notExists() throws Exception {
        assertThat(codeCoverageRepository.findByJobId(1000L), IsEmptyCollection.empty());
    }

    @Test
    public void testFindByModuleId_notExists() throws Exception {
        assertThat(codeCoverageRepository.findByModuleId(1000L), IsEmptyCollection.empty());
    }
*/
}
