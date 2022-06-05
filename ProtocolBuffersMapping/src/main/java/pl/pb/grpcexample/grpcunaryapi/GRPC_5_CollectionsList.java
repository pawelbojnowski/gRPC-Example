package pl.pb.grpcexample.grpcunaryapi;

import java.io.IOException;
import pl.pb.grpcexample.grpcmapping.Address;
import pl.pb.grpcexample.grpcmapping.User;

public class GRPC_5_CollectionsList {

  public static void main(String[] args) throws IOException {

    Address address1 = Address.newBuilder()
        .setCity("Madrid")
        .setCountry("Spain")
        .setStreet("Gaztambide")
        .build();

    Address address2 = Address.newBuilder()
        .setCity("Madrid")
        .setCountry("Spain")
        .setStreet("C. de Isaac Peral, 24")
        .build();

    User user = User.newBuilder()
        .setFirstname("Cristiano")
        .setLastname("Ronaldo")
        .setUsername("CR9")
        .addAddresses(address1)
        .addAddresses(address2)
        //or you can add list with address
        //.addAllAddresses(Arrays.asList(address1, address2))
        .build();

    System.out.println(user);
    /**
     * username: "CR9"
     * firstname: "Cristiano"
     * lastname: "Ronaldo"
     * addresses {
     *   city: "Madrid"
     *   street: "Gaztambide"
     *   country: "Spain"
     * }
     * addresses {
     *   city: "Madrid"
     *   street: "C. de Isaac Peral, 24"
     *   country: "Spain"
     * }
     */

  }

}
