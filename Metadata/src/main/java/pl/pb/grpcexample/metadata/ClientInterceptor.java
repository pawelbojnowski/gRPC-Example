package pl.pb.grpcexample.metadata;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.UUID;

public class ClientInterceptor implements io.grpc.ClientInterceptor {

  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

    return new SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
      @Override
      public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
        //add header to request
        headers.put(Metadata.Key.of("TraceId", Metadata.ASCII_STRING_MARSHALLER), UUID.randomUUID().toString());
        super.start(responseListener, headers);
      }
    };
  }
}
