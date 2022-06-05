package pl.pb.grpcexample.context;


import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class ContextServiceImpl extends ContextServiceGrpc.ContextServiceImplBase {

  @Override
  public void run(Empty request, StreamObserver<Empty> responseObserver) {
    Object contextRequestId = ConstantContext.CONTEXT_REQUEST_ID.get();
    System.out.println("\nProcess request - Request_id: " + contextRequestId);
    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }


}

