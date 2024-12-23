package kr.co.farm.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

import kr.co.farm.notify.Greeting;
import kr.co.farm.notify.HelloMessage;

//@Controller
public class GreetingController {


  @MessageMapping("/hello") //클라이언트에서 /hello 로 publish할때 연결되는 메시지수신
  @SendTo("/topic/greetings") //수신메시지를 보낼 구독자 지정
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }

}