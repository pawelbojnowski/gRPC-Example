package pl.pb.grpcexample.grpcunaryapi;

import java.io.IOException;
import pl.pb.grpcexample.grpcmapping.Car;
import pl.pb.grpcexample.grpcmapping.Engine;

public class GRPC_4_Composition {

  public static void main(String[] args) throws IOException {

    Engine engine = Engine.newBuilder()
        .setModel("B48B20B")
        .setCylindersCount(4)
        .setHorsePower(252)
        .setSize(2.0f)
        .build();

    Car car = Car.newBuilder()
        .setBrand("BMW")
        .setModel("G30")
        .setEngine(engine)
        .build();

    System.out.println(car);
    /**
     * brand: "BMW"
     * model: "G30"
     * engine {
     *   model: "B48B20B"
     *   cylindersCount: 4
     *   size: 2.0
     *   horsePower: 252
     * }
     */

  }

}
