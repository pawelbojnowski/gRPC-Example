package pl.pb.grpcexample.grpcunaryapi;

import java.io.IOException;
import java.util.Map;
import pl.pb.grpcexample.grpcmapping.News;

public class GRPC_5_CollectionsMap {

  public static void main(String[] args) throws IOException {

    Map<String, String> attachemnet = Map.of(
        "Oracler New", "http://oracler.com",
        "BestId - Java 128 - released", "http://bestit.com/J18R"
    );

    News news = News.newBuilder()
        .setId(1024)
        .setTitle("Java 128 - released")
        .setContent("Java 128 was released yesterday")
        .putAllAttachment(attachemnet)
        .build();

    System.out.println(news);
    /**
     * id: 1024
     * title: "Java 128 - released"
     * content: "Java 128 was released yesterday"
     * attachment {
     *   key: "BestId - Java 128 - released"
     *   value: "http://bestit.com/J18R"
     * }
     * attachment {
     *   key: "Oracler New"
     *   value: "http://oracler.com"
     * }
     */
  }

}
