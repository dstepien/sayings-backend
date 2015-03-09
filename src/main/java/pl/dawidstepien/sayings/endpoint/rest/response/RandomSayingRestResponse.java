package pl.dawidstepien.sayings.endpoint.rest.response;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

import pl.dawidstepien.sayings.model.SayingEntity;

public class RandomSayingRestResponse implements RestResponse {

  private final SayingEntity saying;

  public RandomSayingRestResponse(SayingEntity saying) {
    this.saying = saying;
  }

  public Response build() {
    return Response.ok(convertToJson(saying).toString()).build();
  }

  private JSONObject convertToJson(SayingEntity saying) {
    JSONObject json = new JSONObject();
    json.put("id", saying.getId());
    json.put("content", saying.getContent());
    json.put("author", saying.getAuthor());
    return json;
  }
}
