package pl.pb.grpcexample.context;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import java.util.UUID;
import pl.pb.grpcexample.context.ContextServiceGrpc.ContextServiceBlockingStub;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    Metadata metadata = new Metadata();
    metadata.put(Metadata.Key.of("request-id", Metadata.ASCII_STRING_MARSHALLER), UUID.randomUUID().toString());

    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .intercept(MetadataUtils.newAttachHeadersInterceptor(metadata))
        .usePlaintext()
        .build();

    //Example stub request

    ContextServiceBlockingStub ContextServiceBlockingStub = ContextServiceGrpc.newBlockingStub(managedChannel);
    ContextServiceBlockingStub.run(Empty.getDefaultInstance());
    System.out.println("Request was processed");
  }
}
