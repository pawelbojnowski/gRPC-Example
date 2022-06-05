package pl.pb.grpcexample.clientinterceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeadlineInterceptorWhichAllowsOverriding implements ClientInterceptor {

  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
    Deadline deadline = callOptions.getDeadline();
    if (Objects.isNull(deadline)) {
      deadline = Deadline.after(2, TimeUnit.SECONDS);
    }
    return next.newCall(method, callOptions.withDeadline(deadline));
  }
}
