package cl.vice.back.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"insurance\"")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Insurance {

    @Id
    @Schema(description = "Unique Insurance ID", example = "1")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Schema(description = "Name of the insurance", example = "Seguro APV")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
}
