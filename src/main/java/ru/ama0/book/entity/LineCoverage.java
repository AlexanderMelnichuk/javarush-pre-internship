package ru.ama0.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "coverage_line_coverage")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineCoverage {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer jobId;
    private Integer buildId;
    private Integer testType;
    private Integer nodeId;
    private String filepath;
    private String filename;
    private String svnUrl;
    private Integer totalLines;
    private String coveredLines;
    private Integer pvId;
}
