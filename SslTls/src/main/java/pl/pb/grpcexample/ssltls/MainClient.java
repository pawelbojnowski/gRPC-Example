package pl.pb.grpcexample.ssltls;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import javax.net.ssl.SSLException;
import pl.pb.grpcexample.ssl_tls.SslTlsResponse;
import pl.pb.grpcexample.ssl_tls.SslTlsServiceGrpc;
import pl.pb.grpcexample.ssl_tls.SslTlsServiceGrpc.SslTlsServiceBlockingStub;

public class MainClient {


  public static void main(String[] args) throws SSLException {

    SslContext sslContext = GrpcSslContexts.forClient()
        .trustManager(FileUtil.loadFile("ca.cert.pem"))
        .build();

    //Create connection
    ManagedChannel managedChannel = NettyChannelBuilder.forAddress("localhost", 6000)
        .sslContext(sslContext)
//        .usePlaintext()
        .build();

    //Example stub request
    SslTlsServiceBlockingStub deadlineServiceBlockingStub = SslTlsServiceGrpc.newBlockingStub(managedChannel);
    SslTlsResponse response = deadlineServiceBlockingStub.run(Empty.getDefaultInstance());
    System.out.println("Response: " + response.getMessage());
  }
}
