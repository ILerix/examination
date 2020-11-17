package testing.core.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;


//@Entity
//@Table(name = "test_t")
public class Test {

  private UUID id;

  private String title;

  private List<Question> questions;
}
