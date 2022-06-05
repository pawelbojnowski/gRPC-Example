package pl.pb.grpcexample.metadata;

import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;

public class ServerInterceptor implements io.grpc.ServerInterceptor {

  public static final Key<String> TRACE_ID = Key.of("traceid", Metadata.ASCII_STRING_MARSHALLER);
  public static final Key<String> REQUEST_ID = Key.of("requestid", Metadata.ASCII_STRING_MARSHALLER);

  @Override
  public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    System.out.println(TRACE_ID + ": " + headers.get(TRACE_ID));
    System.out.println(REQUEST_ID + ": " + headers.get(REQUEST_ID));
    return next.startCall(call, headers);
  }
}
