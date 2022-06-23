package pl.pb.grpcexample.callcredentials;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import java.util.concurrent.Executor;

public class UserTokenCallCredentials extends CallCredentials {

  private static final Metadata.Key userToken = Metadata.Key.of("user-token", Metadata.ASCII_STRING_MARSHALLER);
  private final String tokenValue;

  public UserTokenCallCredentials(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  @Override
  public void applyRequestMetadata(RequestInfo requestInfo, Executor appExecutor, MetadataApplier applier) {
    appExecutor.execute(() -> {
      Metadata metadata = new Metadata();
      metadata.put(userToken, tokenValue);
      applier.apply(metadata);
    });
  }

  @Override
  public void thisUsesUnstableApi() {
    //Not implemented
  }
}
