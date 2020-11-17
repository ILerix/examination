package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutQuestion {

  private UUID id;

  private ZonedDateTime registrationDate;

  private UserType type;

}
