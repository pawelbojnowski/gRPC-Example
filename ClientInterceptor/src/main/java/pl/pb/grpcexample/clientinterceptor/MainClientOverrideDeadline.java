package pl.pb.grpcexample.clientinterceptor;

import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import pl.pb.grpcexample.clientinterceptorin.ClientInterceptorServiceGrpc;
import pl.pb.grpcexample.clientinterceptorin.ClientInterceptorServiceGrpc.ClientInterceptorServiceBlockingStub;
import pl.pb.grpcexample.clientinterceptorin.DeadlineRequest;
import pl.pb.grpcexample.clientinterceptorin.DeadlineResponse;

public class MainClientOverrideDeadline {

  public static final DeadlineRequest REQUEST = DeadlineRequest.newBuilder().setProcessingSeconds(5).build();

  public static void main(String[] args) throws InterruptedException {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .intercept(new DeadlineInterceptorWhichAllowsOverriding())
        .usePlaintext()
        .build();

    //Example stub request
    runRunWithGlobalDeadline(managedChannel);
    runRunWithOverridingDeadline(managedChannel);

  }

  private static void runRunWithGlobalDeadline(ManagedChannel managedChannel) {
    try {
      ClientInterceptorServiceBlockingStub deadlineServiceBlockingStub = ClientInterceptorServiceGrpc.newBlockingStub(managedChannel);

      Iterator<DeadlineResponse> responses = deadlineServiceBlockingStub.run(REQUEST);
      responses.forEachRemaining(response -> System.out.println("Response: " + response.getMessage()));
    } catch (Exception e) {
      System.out.println("Exception after runWithContext(): " + e.getMessage());
    }
  }

  private static void runRunWithOverridingDeadline(ManagedChannel managedChannel) {
    try {
      ClientInterceptorServiceBlockingStub deadlineServiceBlockingStub = ClientInterceptorServiceGrpc.newBlockingStub(managedChannel)
          //Overriding Deadline
          .withDeadline(Deadline.after(4, TimeUnit.SECONDS)); //We don't need this, we have global own interceptor 'DeadlineInterceptor'

      Iterator<DeadlineResponse> responses = deadlineServiceBlockingStub.run(REQUEST);
      responses.forEachRemaining(response -> System.out.println("Response: " + response.getMessage()));
    } catch (Exception e) {
      System.out.println("Exception after runWithContext(): " + e.getMessage());
    }
  }

}
