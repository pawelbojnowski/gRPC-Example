package pl.pb.grpcexample.grpcunaryapi;

import pl.pb.grpcexample.grpcmapping.Client;
import pl.pb.grpcexample.grpcmapping.ClientType;

public class GRPC_6_Enum {

  public static void main(String[] args) {
    Client warnerBros = Client.newBuilder()
        .setName("Warner Bros")
        .setClientType(ClientType.PREMIUM)
        .build();
    System.out.println(warnerBros);
    /**
     * name: "Warner Bros"
     * ClientType: PREMIUM
     */

    Client tesla = Client.newBuilder()
        .setName("Tesla")
        //.setClientType(...) is not set
        .build();
    System.out.println(tesla);
    /**
     * name: "Tesla"
     *              <--- ClientType is missing
     */

    System.out.println(tesla.getClientType());
    /**
     * but 'tesla.getClientType()' r return NKNOWN_CLIENT_TYPE
     */

  }

}
