package pl.pb.grpcexample.callcredentials;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class AuthenticationInterceptor implements ServerInterceptor {

  final static Metadata.Key<String> USER_TOKEN = Metadata.Key.of("user-token", Metadata.ASCII_STRING_MARSHALLER);

  @Override
  public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

    String token = (String) headers.get(USER_TOKEN);

    if ("correct_token_value".equals(token)) {
      System.out.println("Request with correct token: " + token);
      return next.startCall(call, headers);
    } else {
      System.out.println("Request with incorrect token: " + token);
      Status userSentErrorInRequest = Status.UNAUTHENTICATED.withDescription("User sent incorrect token in request");
      call.close(userSentErrorInRequest, headers);
    }

    return next.startCall(call, headers);
  }
}
