package pl.pb.grpcexample.ssltls;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import java.io.IOException;

public class MainServer {

  public static void main(String[] args) throws IOException, InterruptedException {

    SslContext sslContext = GrpcSslContexts.configure(
        GrpcSslContexts.forServer(
            FileUtil.loadFile("localhost.crt"),
            FileUtil.loadFile("localhost.pem")
        )
    ).build();
    Server server = NettyServerBuilder.forPort(6000)
        .sslContext(sslContext)
        .addService(new SslTlsService())
        .build();

    server.start();
    server.awaitTermination();
  }


}
