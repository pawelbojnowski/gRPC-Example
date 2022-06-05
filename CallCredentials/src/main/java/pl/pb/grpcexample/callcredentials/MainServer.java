package pl.pb.grpcexample.callcredentials;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MainServer {

  public static void main(String[] args) throws IOException, InterruptedException {

    Server server = ServerBuilder.forPort(6000)
        .addService(new CallCredentialsServiceImpl())
        .intercept(new AuthenticationInterceptor())
        .build();

    server.start();
    server.awaitTermination();
  }

}
