package testing.core.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import testing.core.entity.types.UserType;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "user_t")
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  private Integer externalId;

  @CreationTimestamp
  private ZonedDateTime registrationDate;

  @Enumerated(EnumType.STRING)
  private UserType type;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Question> questions;
}
