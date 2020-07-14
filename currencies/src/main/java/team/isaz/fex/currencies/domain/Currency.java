package team.isaz.fex.currencies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@With
public class Currency {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    Double rate;
}
