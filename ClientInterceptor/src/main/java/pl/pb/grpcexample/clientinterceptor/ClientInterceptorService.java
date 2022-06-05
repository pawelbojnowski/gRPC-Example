package pl.pb.grpcexample.clientinterceptor;


import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;
import pl.pb.grpcexample.clientinterceptorin.ClientInterceptorServiceGrpc;
import pl.pb.grpcexample.clientinterceptorin.DeadlineRequest;
import pl.pb.grpcexample.clientinterceptorin.DeadlineResponse;

public class ClientInterceptorService extends ClientInterceptorServiceGrpc.ClientInterceptorServiceImplBase {


  @Override
  public void run(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("Process request: ");

    for (int i = 0; i < request.getProcessingSeconds(); i++) {
      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
      if (Context.current().isCancelled()) {
        System.out.println("Stop sending response!");
        break;
      }
      sendResponse(i, responseObserver);
    }
    responseObserver.onCompleted();
  }

  private void sendResponse(int i, StreamObserver<DeadlineResponse> responseObserver) {
    DeadlineResponse response = DeadlineResponse.newBuilder()
        .setMessage("OK - item: " + (i + 1))
        .build();
    responseObserver.onNext(response);

    System.out.println("Response " + (i + 1) + " was send");
  }
}

