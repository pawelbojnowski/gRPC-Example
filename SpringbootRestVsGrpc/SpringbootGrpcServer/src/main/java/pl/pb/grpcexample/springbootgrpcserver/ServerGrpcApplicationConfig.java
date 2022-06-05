package pl.pb.grpcexample.springbootgrpcserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pb.grpcexample.springbootcommons.TextProcessor;

@Configuration
public class ServerGrpcApplicationConfig {

  @Bean
  TextProcessor textProcessor() {
    return new TextProcessor();
  }

}
