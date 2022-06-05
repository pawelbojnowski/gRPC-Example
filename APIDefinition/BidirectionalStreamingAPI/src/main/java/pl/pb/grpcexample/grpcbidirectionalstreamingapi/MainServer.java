package pl.pb.grpcexample.grpcbidirectionalstreamingapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MainServer {

  public static void main(String[] args) throws IOException, InterruptedException {

    Server server = ServerBuilder.forPort(6000)
        .addService(new ChatService())
        .build();

    server.start();
    server.awaitTermination();
  }

}
