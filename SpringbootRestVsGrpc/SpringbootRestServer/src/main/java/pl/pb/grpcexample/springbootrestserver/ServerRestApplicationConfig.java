package pl.pb.grpcexample.springbootrestserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pb.grpcexample.springbootcommons.TextProcessor;

@Configuration
public class ServerRestApplicationConfig {

  @Bean
  TextProcessor textProcessor() {
    return new TextProcessor();
  }

}
