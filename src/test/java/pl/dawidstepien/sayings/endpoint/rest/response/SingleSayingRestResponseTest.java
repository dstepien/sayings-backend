package pl.dawidstepien.sayings.endpoint.rest.response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.dawidstepien.sayings.model.SayingEntity;

@RunWith(MockitoJUnitRunner.class)
public class SingleSayingRestResponseTest {

  private static final String CONTENT = "Lorem ipsum";

  private static final String AUTHOR = "John Doe";

  private static final long ID = 12345L;

  @Mock
  private SayingEntity saying;

  @Test
  public void shouldReturnJsonResponse() {
    // given
    when(saying.getContent()).thenReturn(CONTENT);
    when(saying.getAuthor()).thenReturn(AUTHOR);
    when(saying.getId()).thenReturn(ID);
    String expected = String.format("{\"author\":\"%s\",\"id\":%d,\"content\":\"%s\"}", AUTHOR, ID, CONTENT);
    SingleSayingRestResponse response = new SingleSayingRestResponse(saying);

    // when
    Response result = response.build();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
    assertEquals(expected, result.getEntity().toString());
  }
}