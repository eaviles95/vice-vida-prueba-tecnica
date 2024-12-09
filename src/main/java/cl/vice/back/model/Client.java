package cl.vice.back.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"client\"")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @Schema(description = "Unique Client ID", example = "1")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Schema(description = "Client Name", example = "John Doe")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Schema(description = "Rut number", example = "12345678-9")
    @Column(name = "rut")
    @Getter
    @Setter
    private String rut;

}
