package pl.pb.grpcexample.metadataerrorhandler;


import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

public class UserService extends DeadlineServiceGrpc.DeadlineServiceImplBase {


  @Override
  public void run(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("Process request: ");

    Key<String> requestId = Key.of("custom-error-message", Metadata.ASCII_STRING_MARSHALLER);
    Metadata metadata = new Metadata();
    metadata.put(requestId, "Example of error handler via Metadata. This request returns always this error message");

    StatusRuntimeException statusRuntimeException = Status.INTERNAL.asRuntimeException(metadata);

    responseObserver.onError(statusRuntimeException);
    responseObserver.onCompleted();
  }


  @Override
  public void run1(DeadlineRequest request, StreamObserver<DeadlineResponse> responseObserver) {
    System.out.println("Process request: ");

    Key<ErrorMessage> requestId = ProtoUtils.keyForProto(ErrorMessage.getDefaultInstance());
    ErrorMessage errorMessage = ErrorMessage.newBuilder().setTitle("Validation Error").setDescription("AAA").build();

    Metadata metadata = new Metadata();
    metadata.put(requestId, errorMessage);

    StatusRuntimeException statusRuntimeException = Status.INTERNAL.asRuntimeException(metadata);

    responseObserver.onError(statusRuntimeException);
    responseObserver.onCompleted();
  }

}

