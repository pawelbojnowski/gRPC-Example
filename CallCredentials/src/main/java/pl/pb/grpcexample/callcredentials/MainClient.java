package pl.pb.grpcexample.callcredentials;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import java.util.List;
import java.util.UUID;
import pl.pb.grpcexample.callcredentials.CallCredentialsServiceGrpc.CallCredentialsServiceBlockingStub;

public class MainClient {


  public static void main(String[] args) {

    //Create connection
    Metadata metadata = new Metadata();
    metadata.put(Metadata.Key.of("RequestId", Metadata.ASCII_STRING_MARSHALLER), UUID.randomUUID().toString());

    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    //Example stub request

    CallCredentialsServiceBlockingStub callCredentialsServiceBlockingStub = CallCredentialsServiceGrpc.newBlockingStub(managedChannel);

    List.of("correct_token_value", "incorrect_token_value").forEach(tokenValue -> {
      try {
        System.out.println("\nRequest with token: " + tokenValue);
        UserTokenCallCredentials credentials = new UserTokenCallCredentials(tokenValue);
        callCredentialsServiceBlockingStub
            .withCallCredentials(credentials)
            .run(Empty.getDefaultInstance());

        System.out.println("Requested was processed correctly");
      } catch (Exception e) {
        System.out.println("Response: " + e.getMessage());
      }
    });

  }

}
