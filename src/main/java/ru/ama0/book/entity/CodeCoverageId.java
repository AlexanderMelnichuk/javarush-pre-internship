package ru.ama0.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CodeCoverageId implements Serializable {
    @NonNull
    @JsonIgnore
    private Integer jobId;

    @NonNull
    @JsonProperty("id")
    private Integer nodeId;
}
