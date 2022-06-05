package pl.pb.grpcexample.ssltls;


import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import pl.pb.grpcexample.ssl_tls.SslTlsResponse;
import pl.pb.grpcexample.ssl_tls.SslTlsServiceGrpc;

public class SslTlsService extends SslTlsServiceGrpc.SslTlsServiceImplBase {


  @Override
  public void run(Empty request, StreamObserver<SslTlsResponse> responseObserver) {
    System.out.println("Process request");
    SslTlsResponse response = SslTlsResponse.newBuilder()
        .setMessage("OK")
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}

