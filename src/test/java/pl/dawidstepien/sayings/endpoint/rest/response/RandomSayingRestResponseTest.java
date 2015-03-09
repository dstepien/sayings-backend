package pl.dawidstepien.sayings.endpoint.rest.response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.junit.Test;

import pl.dawidstepien.sayings.model.SayingEntity;

public class RandomSayingRestResponseTest {

  private static final String CONTENT = "content";

  private static final String AUTHOR = "author";

  private static final long ID = 12345L;

  @Test
  public void shouldReturnJsonResponse() {
    // given
    SayingEntity saying = mock(SayingEntity.class);
    when(saying.getContent()).thenReturn(CONTENT);
    when(saying.getAuthor()).thenReturn(AUTHOR);
    when(saying.getId()).thenReturn(ID);
    RandomSayingRestResponse response = new RandomSayingRestResponse(saying);

    // when
    Response result = response.build();

    // then
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
    assertEquals(CONTENT, new JSONObject(result.getEntity().toString()).get("content"));
    assertEquals(AUTHOR, new JSONObject(result.getEntity().toString()).get("author"));
    assertEquals(String.valueOf(ID), String.valueOf(new JSONObject(result.getEntity().toString()).get("id")));
  }
}