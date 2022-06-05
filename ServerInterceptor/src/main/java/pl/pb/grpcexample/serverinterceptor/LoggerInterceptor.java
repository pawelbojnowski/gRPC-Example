package pl.pb.grpcexample.serverinterceptor;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class LoggerInterceptor implements ServerInterceptor {


  @Override
  public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    System.out.println("Methodo: '" + call.getMethodDescriptor().getFullMethodName() + "' will be executed ");

    return next.startCall(
        new SimpleForwardingServerCall<ReqT, RespT>(call) {
          @Override
          public void sendMessage(RespT message) {
            super.sendMessage(message);
            System.out.println("Methodo: '" + call.getMethodDescriptor().getFullMethodName() + "' will be executed ");
          }
        },
        headers);

  }
}
