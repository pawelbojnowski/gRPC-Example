package pl.pb.grpcexample.context;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ContextInterceptor implements ServerInterceptor {

  final static Metadata.Key REQUEST_ID = Metadata.Key.of("request-id", Metadata.ASCII_STRING_MARSHALLER);

  @Override
  public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    String requestId = (String) headers.get(REQUEST_ID);
    Context context = Context.current().withValue(ConstantContext.CONTEXT_REQUEST_ID, requestId);
    return Contexts.interceptCall(context, call, headers, next);
  }
}
