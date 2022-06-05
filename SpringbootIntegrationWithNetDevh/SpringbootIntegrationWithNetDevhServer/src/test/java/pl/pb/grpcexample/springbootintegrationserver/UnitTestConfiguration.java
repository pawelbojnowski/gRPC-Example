package pl.pb.grpcexample.springbootintegrationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class UnitTestConfiguration {

  @Bean
  TextProcessor textProcessor() {
    return new TextProcessor();
  }

  @Bean
  TextController textController(@Autowired TextProcessor textProcessor) {
    return new TextController(textProcessor);
  }
}
