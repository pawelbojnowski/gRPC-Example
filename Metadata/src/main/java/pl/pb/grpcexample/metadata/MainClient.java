package pl.pb.grpcexample.metadata;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import java.util.UUID;
import pl.pb.grpcexample.metadata.MetadataServiceGrpc.MetadataServiceBlockingStub;

public class MainClient {

  public static void main(String[] args) {

    //Create metadata/headers
    Metadata metadata = new Metadata();
    metadata.put(Metadata.Key.of("RequestId", Metadata.ASCII_STRING_MARSHALLER), UUID.randomUUID().toString());

    //Create which add metadata/headers
    ClientInterceptor clientInterceptor = new ClientInterceptor();

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .intercept(clientInterceptor)
        //Add metadata/headers
        .intercept(MetadataUtils.newAttachHeadersInterceptor(metadata))
        .usePlaintext()
        .build();

    //Example stub request
    MetadataServiceBlockingStub deadlineServiceBlockingStub = MetadataServiceGrpc.newBlockingStub(managedChannel);
    deadlineServiceBlockingStub.run(Empty.getDefaultInstance());
  }
}
