package pl.pb.grpcexample.testingwithmockingstab;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;

public class MainServer {

  public static void main(String[] args) throws IOException, InterruptedException {

    Server server = NettyServerBuilder.forPort(6000)
        .addService(new ExampleServerService())
        .build();

    server.start();
    server.awaitTermination();
  }


}
