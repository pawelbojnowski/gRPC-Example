package pl.pb.grpcexample.testingwithmockingstab;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import pl.pb.grpcexample.testingwithmockingstab.ExampleServiceGrpc.ExampleServiceBlockingStub;

public class MainClient {


  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = NettyChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    //Example stub request
    ExampleServiceBlockingStub deadlineServiceBlockingStub = ExampleServiceGrpc.newBlockingStub(managedChannel);
    ExampleResponse response = deadlineServiceBlockingStub.run(ExampleRequest.newBuilder().setMessage("TEST MESSAGE").build());
    System.out.println("Response: " + response.getMessage());
  }
}
