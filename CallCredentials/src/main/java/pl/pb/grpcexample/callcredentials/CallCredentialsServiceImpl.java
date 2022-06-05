package pl.pb.grpcexample.callcredentials;


import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class CallCredentialsServiceImpl extends CallCredentialsServiceGrpc.CallCredentialsServiceImplBase {

  @Override
  public void run(Empty request, StreamObserver<Empty> responseObserver) {
    System.out.println("\nProcess request\n");
    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }
}

