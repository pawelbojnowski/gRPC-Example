package pl.pb.grpcexample.metadata;


import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class MetadataService extends MetadataServiceGrpc.MetadataServiceImplBase {


  @Override
  public void run(Empty request, StreamObserver<Empty> responseObserver) {
    System.out.println("Process request");
    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }

}

