package pl.pb.grpcexample.metadataerrorhandler;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import pl.pb.grpcexample.metadataerrorhandler.DeadlineServiceGrpc.DeadlineServiceBlockingStub;

public class MainClient {

  public static final DeadlineRequest REQUEST = DeadlineRequest.newBuilder().setProcessingSeconds(5).build();

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    run(managedChannel);
    run1(managedChannel);
  }

  private static void run(ManagedChannel managedChannel) {
    //Example stub request
    DeadlineServiceBlockingStub deadlineServiceBlockingStub = DeadlineServiceGrpc.newBlockingStub(managedChannel);
    try {
      deadlineServiceBlockingStub.run(REQUEST);
    } catch (Throwable e) {
      Key<String> requestId = Key.of("custom-error-message", Metadata.ASCII_STRING_MARSHALLER);
      System.out.println("Error Message: " + Status.trailersFromThrowable(e).get(requestId));
    }
  }

  private static void run1(ManagedChannel managedChannel) {
    //Example stub request
    DeadlineServiceBlockingStub deadlineServiceBlockingStub = DeadlineServiceGrpc.newBlockingStub(managedChannel);
    try {
      deadlineServiceBlockingStub.run1(REQUEST);
    } catch (Throwable e) {
      Key<ErrorMessage> requestId = ProtoUtils.keyForProto(ErrorMessage.getDefaultInstance());
      System.out.println("Title of error message: " + Status.trailersFromThrowable(e).get(requestId).getTitle());
      System.out.println("Description of error message: " + Status.trailersFromThrowable(e).get(requestId).getDescription());
    }
  }
}
