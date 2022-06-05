package pl.pb.grpcexample.serverinterceptor;


import io.grpc.stub.StreamObserver;
import pl.pb.grpcexample.interceptorinserver.DeadlineRequest;
import pl.pb.grpcexample.interceptorinserver.DeadlineResponse;
import pl.pb.grpcexample.interceptorinserver.DeadlineServiceGrpc.DeadlineServiceImplBase;

public class ServerInterceptorService extends DeadlineServiceImplBase {


  @Override
  public void run(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("Process request: ");

    DeadlineResponse response = DeadlineResponse.newBuilder()
        .setMessage("OK")
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}

