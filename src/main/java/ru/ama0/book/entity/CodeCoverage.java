package ru.ama0.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coverage_code_coverage")
public class CodeCoverage {

    public static final String UNNAMED_PACKAGE = "unnamed package";

    @Id
    @Embedded
    private CodeCoverageId id;

    @JsonIgnore
    private Integer buildId;

    @JsonIgnore
    private Integer parentNode;

    @JsonIgnore
    @Enumerated(EnumType.ORDINAL)
    private ElementType elementType;

    @JsonIgnore
    private String absoluteName;

    @JsonIgnore
    private String signature;

    private String relativeName;

    @JsonProperty("classes")
    private Integer totalClasses;

    @JsonProperty("classesCovered")
    private Integer coveredClasses;

    @JsonProperty("methods")
    private Integer totalMethods;

    @JsonProperty("methodsCovered")
    private Integer coveredMethods;

    @JsonProperty("complexity")
    private Integer totalComplexity;

    @JsonProperty("complexityCovered")
    private Integer coveredComplexity;

    @JsonProperty("branches")
    private Integer totalBranches;

    @JsonProperty("branchesCovered")
    private Integer coveredBranches;

    @JsonProperty("lines")
    private Integer totalLines;

    @JsonProperty("linesCovered")
    private Integer coveredLines;

    @JsonProperty("instructions")
    private Integer totalInstructions;

    @JsonProperty("instructionsCovered")
    private Integer coveredInstructions;

    private Integer sourceId;

    private Integer startingLine;

    private Integer modifiedCoveredLines;

    private Integer modifiedMissedLines;

    @JsonIgnore
    private Integer pvId;

    @JsonProperty
    public String getName() {
        return StringUtils.isBlank(relativeName) ? UNNAMED_PACKAGE : relativeName;
    }

    public enum ElementType {
        BUNDLE, PACKAGE, CLASS, METHOD;

        /*
         * @return value representing this type in database
         */
        public int getDatabaseValue() {
            return ordinal();
        }
    }
}
