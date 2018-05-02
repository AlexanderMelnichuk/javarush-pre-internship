package ru.ama0.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ama0.book.entity.CodeCoverage;
import ru.ama0.book.entity.LineCoverage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileCoverage {

    @JsonProperty("svnURL")
    private String svnUrl;

    private String absolutePath;
    private String name;
    private Integer classes;
    private Integer classesCovered;
    private Integer methods;
    private Integer methodsCovered;
    private Integer complexity;
    private Integer complexityCovered;
    private Integer branches;
    private Integer branchesCovered;
    private Integer lines;
    private Integer linesCovered;
    private Integer instructions;
    private Integer instructionsCovered;
    private Integer id;

    @JsonIgnore
    private String lineCoveredLines;

    public FileCoverage(CodeCoverage codeCoverage, LineCoverage lineCoverage) {
        this.svnUrl = lineCoverage.getSvnUrl();
        this.absolutePath = lineCoverage.getFilepath();
        this.name = lineCoverage.getFilename();
        this.lineCoveredLines = lineCoverage.getCoveredLines();

        this.classes = codeCoverage.getTotalClasses();
        this.classesCovered = codeCoverage.getCoveredClasses();
        this.methods = codeCoverage.getTotalMethods();
        this.methodsCovered = codeCoverage.getCoveredMethods();
        this.complexity = codeCoverage.getTotalComplexity();
        this.complexityCovered = codeCoverage.getCoveredComplexity();
        this.branches = codeCoverage.getTotalBranches();
        this.branchesCovered = codeCoverage.getCoveredBranches();
        this.lines = codeCoverage.getTotalLines();
        this.linesCovered = codeCoverage.getCoveredLines();
        this.instructions = codeCoverage.getTotalInstructions();
        this.instructionsCovered = codeCoverage.getCoveredInstructions();
        this.id = codeCoverage.getId().getNodeId();
    }

    @SuppressWarnings("unused") // this becomes a part of JSON response. See CoverageJobResource
    public List<Integer> getLineStatus() {
        if (null == lineCoveredLines) {
            return Collections.emptyList();
        }
        final byte[] asBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(lineCoveredLines);
        // Bit 0 is not filled in coverage processors, so we do not need to adjust index here.
        return BitSet.valueOf(asBytes).stream().boxed().collect(Collectors.toList());
    }
}
