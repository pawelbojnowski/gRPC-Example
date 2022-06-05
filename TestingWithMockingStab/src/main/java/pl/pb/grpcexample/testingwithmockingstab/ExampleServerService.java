package pl.pb.grpcexample.testingwithmockingstab;


import io.grpc.stub.StreamObserver;

public class ExampleServerService extends ExampleServiceGrpc.ExampleServiceImplBase {

  @Override
  public void run(ExampleRequest request, StreamObserver<ExampleResponse> responseObserver) {
    System.out.println("Response: " + request.getMessage());
    ExampleResponse response = ExampleResponse.newBuilder()
        .setMessage("TEST REQUEST")
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}

