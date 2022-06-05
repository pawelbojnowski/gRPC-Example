package pl.pb.grpcexample.serverinterceptor;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import pl.pb.grpcexample.interceptorinserver.DeadlineRequest;
import pl.pb.grpcexample.interceptorinserver.DeadlineResponse;
import pl.pb.grpcexample.interceptorinserver.DeadlineServiceGrpc;
import pl.pb.grpcexample.interceptorinserver.DeadlineServiceGrpc.DeadlineServiceBlockingStub;

public class MainClient {

  public static final DeadlineRequest REQUEST = DeadlineRequest.newBuilder().setProcessingSeconds(5).build();

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    //Example stub request
    DeadlineServiceBlockingStub deadlineServiceBlockingStub = DeadlineServiceGrpc.newBlockingStub(managedChannel);
    DeadlineResponse response = deadlineServiceBlockingStub.run(REQUEST);
    System.out.println("Response: " + response.getMessage());
  }
}
