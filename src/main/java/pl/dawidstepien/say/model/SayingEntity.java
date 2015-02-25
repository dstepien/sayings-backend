package pl.dawidstepien.say.model;

import static javax.persistence.GenerationType.SEQUENCE;
import static pl.dawidstepien.say.model.SayingEntity.FIND_ALL_SAYINGS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_sayings")
@NamedQuery(name = FIND_ALL_SAYINGS, query = "SELECT saying FROM SayingEntity saying")
public class SayingEntity {

  public static final String FIND_ALL_SAYINGS = "FIND_ALL_SAYINGS";

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "t_sayings_seq_generator")
  @SequenceGenerator(name="t_sayings_seq_generator", sequenceName="t_sayings_seq")
  private long id;

  @NotNull
  @Column(nullable = false)
  private String content;

  @NotNull
  @Column(nullable = false)
  private String author;

  public String getContent() {
    return content;
  }
}
