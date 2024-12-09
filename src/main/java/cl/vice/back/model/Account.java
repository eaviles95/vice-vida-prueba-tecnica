package cl.vice.back.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "\"account\"")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @Schema(description = "Unique Account ID", example = "1")
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Schema(description = "ID of the Client associated with the account", example = "1001") // Reemplazo de ApiModelProperty
    @Column(name = "client_id")
    @Getter
    @Setter
    private Integer clientId;

    @Schema(description = "ID of the Insurance associated with the account", example = "2001") // Reemplazo de ApiModelProperty
    @Column(name = "insurance_id")
    @Getter
    @Setter
    private Integer insuranceId;

    @Schema(description = "Balance amount in the account", example = "15000")
    @Column(name = "balance")
    @Getter
    @Setter
    private Integer balance;

}
