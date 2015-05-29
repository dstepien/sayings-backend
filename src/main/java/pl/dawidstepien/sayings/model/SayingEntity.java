package pl.dawidstepien.sayings.model;

import static pl.dawidstepien.sayings.model.SayingEntity.FIND_ALL_SAYINGS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_sayings")
@NamedQuery(name = FIND_ALL_SAYINGS, query = "SELECT saying FROM SayingEntity saying")
@XmlRootElement
public class SayingEntity {

  public static final String FIND_ALL_SAYINGS = "FIND_ALL_SAYINGS";

  @Id
  @GeneratedValue
  private long id;

  @NotNull
  @Column(nullable = false)
  private String content;

  @NotNull
  @Column(nullable = false)
  private String author;

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
