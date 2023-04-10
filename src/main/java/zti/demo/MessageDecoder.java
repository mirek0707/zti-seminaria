/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zti.demo;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

import java.io.StringReader;

/**
 *
 * @author Juneau
 */
public class MessageDecoder implements Decoder.Text<Message> {

  @Override
  public Message decode(String jsonMessage) throws DecodeException {

    JsonObject jsonObject = Json
        .createReader(new StringReader(jsonMessage)).readObject();
    Message message = new Message();
    message.setUsername(jsonObject.getString("username"));
    message.setMessage(jsonObject.getString("message"));
    return message;

  }

  @Override
  public boolean willDecode(String jsonMessage) {
    try {
      // Check if incoming message is valid JSON
      Json.createReader(new StringReader(jsonMessage)).readObject();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void init(EndpointConfig ec) {
    System.out.println("Initializing message decoder");
  }

  @Override
  public void destroy() {
    System.out.println("Destroyed message decoder");
  }

}
