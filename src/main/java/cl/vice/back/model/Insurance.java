package cl.vice.back.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"insurance\"")
@ApiModel(value = "Insurance table", description = "Insurance table")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Insurance {

    @Id
    @ApiModelProperty(value = "Insurance Id")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ApiModelProperty(value = "Name")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
}
