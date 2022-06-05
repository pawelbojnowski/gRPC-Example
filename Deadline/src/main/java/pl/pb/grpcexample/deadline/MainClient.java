package pl.pb.grpcexample.deadline;

import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import pl.pb.grpcexample.deadline.DeadlineServiceGrpc.DeadlineServiceBlockingStub;

public class MainClient {

  public static final DeadlineRequest REQUEST = DeadlineRequest.newBuilder().setCountOfResponse(5).build();

  public static void main(String[] args) throws InterruptedException {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000).usePlaintext().build();

    DeadlineServiceBlockingStub deadlineServiceBlockingStub = DeadlineServiceGrpc.newBlockingStub(managedChannel)
        .withDeadline(Deadline.after(3, TimeUnit.SECONDS));

    //Example stub request
    runWithoutContext(deadlineServiceBlockingStub);
    runWithContext(deadlineServiceBlockingStub);

  }

  private static void runWithoutContext(DeadlineServiceBlockingStub deadlineServiceBlockingStub) {
    try {
      Iterator<DeadlineResponse> responses = deadlineServiceBlockingStub.runWithoutContext(REQUEST);
      responses.forEachRemaining(response -> System.out.println("Response: " + response.getMessage()));
    } catch (Exception e) {
      System.out.println("Exception after runWithoutContext(): " + e.getMessage());
    }
  }

  private static void runWithContext(DeadlineServiceBlockingStub deadlineServiceBlockingStub) {
    try {
      Iterator<DeadlineResponse> responses = deadlineServiceBlockingStub.runWithContext(REQUEST);
      responses.forEachRemaining(response -> System.out.println("Response: " + response.getMessage()));
    } catch (Exception e) {
      System.out.println("Exception after runWithContext(): " + e.getMessage());
    }
  }

}
