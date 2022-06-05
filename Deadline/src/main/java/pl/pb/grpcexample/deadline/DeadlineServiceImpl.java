package pl.pb.grpcexample.deadline;


import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;

public class DeadlineServiceImpl extends DeadlineServiceGrpc.DeadlineServiceImplBase {

  @Override
  public void runWithoutContext(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("\nProcess request WITHOUT checking (gRpc) Context");

    for (int i = 0; i < request.getCountOfResponse(); i++) {
      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
      sendResponse(i, responseObserver, "runWithoutContext");
    }

    responseObserver.onCompleted();
  }


  @Override
  public void runWithContext(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("\nProcess request WITH checking (gRpc) Context");

    for (int i = 0; i < request.getCountOfResponse(); i++) {
      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
      if (Context.current().isCancelled()) {
        System.out.println("The response will be not send !");
        break;
      }
      sendResponse(i, responseObserver, "runWithContext");
    }

    responseObserver.onCompleted();
  }

  private void sendResponse(int i, StreamObserver<DeadlineResponse> responseObserver, String s) {
    DeadlineResponse response = DeadlineResponse.newBuilder()
        .setMessage("OK - item: " + (i + 1))
        .build();
    responseObserver.onNext(response);

    System.out.println("Response  " + (i + 1) + " context is cancelled: " + Context.current().isCancelled() + " for method: " + s);
  }
}

