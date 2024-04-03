package tn.Backend.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Entity
@Table
@SuperBuilder
@DiscriminatorValue("Agent")
@NoArgsConstructor
public class Agent extends User {


}
