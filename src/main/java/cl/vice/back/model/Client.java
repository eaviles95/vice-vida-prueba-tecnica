package cl.vice.back.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"client\"")
@ApiModel(value = "Client table", description = "Client table")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @ApiModelProperty(value = "Client Id")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ApiModelProperty(value = "Name")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ApiModelProperty(value = "Rut")
    @Column(name = "rut")
    @Getter
    @Setter
    private String rut;

}
