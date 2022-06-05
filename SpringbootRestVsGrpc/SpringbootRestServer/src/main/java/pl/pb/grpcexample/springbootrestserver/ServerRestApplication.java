package pl.pb.grpcexample.springbootrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServerRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerRestApplication.class, args);
  }

}
