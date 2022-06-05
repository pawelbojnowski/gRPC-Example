package pl.pb.grpcexample.context;

import io.grpc.Context;

public class ConstantContext {

  public final static Context.Key CONTEXT_REQUEST_ID = Context.key("context-request_id");

}
