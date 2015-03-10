package pl.dawidstepien.sayings.endpoint.rest.response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;

import pl.dawidstepien.sayings.model.SayingEntity;

public class AllSayingsRestResponseTest {

  private static final String CONTENT = "Lorem ipsum";

  private static final String AUTHOR = "John Doe";

  private static final long ID = 12345L;

  @Test
  public void shouldReturnJsonListResponse() {
    // given
    List<SayingEntity> sayings = Arrays.asList(getSayingEntityMock(), getSayingEntityMock(), getSayingEntityMock());
    String expected = String.format("[%s,%s,%s]", getJsonExpectedEntity(), getJsonExpectedEntity(), getJsonExpectedEntity());
    AllSayingsRestResponse response = new AllSayingsRestResponse(sayings);

    // when
    Response result = response.build();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
    assertEquals(expected, result.getEntity().toString());
  }

  private SayingEntity getSayingEntityMock() {
    SayingEntity saying = mock(SayingEntity.class);
    when(saying.getContent()).thenReturn(CONTENT);
    when(saying.getAuthor()).thenReturn(AUTHOR);
    when(saying.getId()).thenReturn(ID);
    return saying;
  }

  private String getJsonExpectedEntity() {
    return String.format("{\"author\":\"%s\",\"id\":%d,\"content\":\"%s\"}", AUTHOR, ID, CONTENT);
  }
}
