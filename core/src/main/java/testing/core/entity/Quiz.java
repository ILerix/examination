package testing.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "test_t")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz { // "Test" often correlate with other classes

  @Id
  @GeneratedValue
  private UUID id;

  private String title;

  @CreationTimestamp
  private ZonedDateTime createDate;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinTable(name = "test_question_t",
          joinColumns = @JoinColumn(name = "test_id"),
          inverseJoinColumns = @JoinColumn(name = "question_id"))
  private List<Question> questions;

}
