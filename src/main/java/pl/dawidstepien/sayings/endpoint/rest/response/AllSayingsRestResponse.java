package pl.dawidstepien.sayings.endpoint.rest.response;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.dawidstepien.sayings.model.SayingEntity;

public class AllSayingsRestResponse implements RestResponse {

  private final List<SayingEntity> sayings;

  public AllSayingsRestResponse(List<SayingEntity> sayings) {
    this.sayings = sayings;
  }

  @Override
  public Response build() {
    return Response.ok(convertToJsonList(sayings).toString()).build();
  }

  private JSONArray convertToJsonList(List<SayingEntity> sayingsEntities) {
    return new JSONArray(sayingsEntities.stream().map(this::convertToJson).collect(Collectors.toList()));
  }

  private JSONObject convertToJson(SayingEntity saying) {
    JSONObject json = new JSONObject();
    json.put("id", saying.getId());
    json.put("content", saying.getContent());
    json.put("author", saying.getAuthor());
    return json;
  }
}
