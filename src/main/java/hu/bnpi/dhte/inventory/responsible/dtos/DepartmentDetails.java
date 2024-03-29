package hu.bnpi.dhte.inventory.responsible.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDetails {

    private Long id;

    private String responsibleNumber;

    private String name;

    private String leaderName;

    private String leaderEmail;
}
