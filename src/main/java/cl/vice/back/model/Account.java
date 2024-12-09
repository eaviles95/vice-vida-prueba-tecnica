package cl.vice.back.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "\"account\"")
@ApiModel(value = "Account table", description = "Account table")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @ApiModelProperty(value = "Account Id")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ApiModelProperty(value = "Client id")
    @Column(name = "client_id")
    @Getter
    @Setter
    private Integer clientId;

    @ApiModelProperty(value = "Insurance id")
    @Column(name = "insurance_id")
    @Getter
    @Setter
    private Integer insuranceId;

    @ApiModelProperty(value = "Balance")
    @Column(name = "balance")
    @Getter
    @Setter
    private Integer balance;

}
