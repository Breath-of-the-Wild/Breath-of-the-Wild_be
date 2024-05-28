package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.service.campingService.CampingResponse.Items;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class ItemsDeserializer extends JsonDeserializer<Items> {

  @Override
  public Items deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // JSON 데이터를 읽어들이는 JsonParser 객체로부터 JsonNode를 가져옴
    JsonNode node = p.getCodec().readTree(p);

    // JSON 노드가 빈 문자열인 경우 null을 반환
    if (node.isTextual() && node.asText().isEmpty()) {
      return null;
    }

    // JsonNode를 CampingResponse.Items 클래스로 역직렬화하여 반환
    return p.getCodec().treeToValue(node, Items.class);
  }
}


/*
Jackson 라이브러리를 사용하여 JSON 데이터를 자바 객체로 역직렬화할 때 사용되는 '
커스텀 역직렬화기인 ItemsDeserializer를 정의
이 역직렬화기는 CampingResponse.Items 클래스에 대한 역직렬화를 담당

여기서 JsonDeserializer는 Jackson에서 제공하는 추상 클래스로,
이를 상속받아서 커스텀 역직렬화 로직을 구현.
JsonDeserializer<Items>에서 <Items>는 역직렬화할 타입을 나타냄

deserialize() 메서드는 JsonParser와 DeserializationContext를 매개변수로 받아
JSON 데이터를 자바 객체로 변환하는 역할을 함.
먼저 JsonParser를 사용하여 JSON 데이터를 읽어들인 후, JsonNode로 변환.
다음, 이 노드를 treeToValue() 메서드를 사용하여 CampingResponse.Items 클래스로 역직렬화함

 */