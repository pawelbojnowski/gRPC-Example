package pl.pb.grpcexample.grpcunaryapi;

import com.google.protobuf.InvalidProtocolBufferException;
import pl.pb.grpcexample.grpcmapping.Book_V1;
import pl.pb.grpcexample.grpcmapping.Book_V2;
import pl.pb.grpcexample.grpcmapping.Book_V3;
import pl.pb.grpcexample.grpcmapping.Book_WhenNameOfFieldIsChanged;
import pl.pb.grpcexample.grpcmapping.Book_WhenTypeOfFieldIsChanged;

public class GRPC_9_BackwardAndForwardCompatibility {

  public static void main(String[] args) throws InvalidProtocolBufferException {
    /**
     * We have we 3 models defined in proto Book_V1,Book_V2,Book_V3
     * Lets imaging that every one model it's next version (Book_V1 -> Book_V2 -> Book_V3);)
     */

    toVersion2FromVersion1();
    toVersion1FromVersion2();
    toVersion1FromVersion3();
    toVersion2FromVersionWhereNameOfFieldIsChanged();
    toVersion2FromVersionWhenTypeOfFieldIsChanged();

  }

  private static void toVersion2FromVersion1() throws InvalidProtocolBufferException {
    System.out.println("-------------fromVersion1ToVersion2-------------");
    Book_V1 bookV1 = Book_V1.newBuilder()
        .setTitle("Clean Code")
        .setAuthor("Robert C. Martin")
        .build();
    System.out.println(bookV1);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     */

    Book_V2 bookV2 = Book_V2.parseFrom(bookV1.toByteArray());
    System.out.println(bookV2);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     */
    System.out.println("Publisher is missing: '" + bookV2.getPublisher() + "'");
  }


  private static void toVersion1FromVersion2() throws InvalidProtocolBufferException {
    System.out.println("-------------fromVersion2ToVersion1-------------");
    Book_V2 bookV2 = Book_V2.newBuilder()
        .setTitle("Clean Code")
        .setAuthor("Robert C. Martin")
        .setPublisher("Helion")
        .build();
    System.out.println(bookV2);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     */

    Book_V1 bookV1 = Book_V1.parseFrom(bookV2.toByteArray());
    System.out.println(bookV1);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * 3: "Helion"
     */
    System.out.println("UnknownFields: " + bookV1.getUnknownFields());
    /**
     *
     UnknownFields: 3: "Helion"
     */
  }

  private static void toVersion1FromVersion3() throws InvalidProtocolBufferException {
    System.out.println("-------------fromVersion3ToVersion1-------------");
    Book_V3 bookV3 = Book_V3.newBuilder()
        .setTitle("Clean Code")
        .setAuthor("Robert C. Martin")
        //.setPublisher("Helion") // <- Publisher was deleted
        .setPages(424)
        .build();

    System.out.println(bookV3);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * pages: 424
     */

    Book_V1 bookV1 = Book_V1.parseFrom(bookV3.toByteArray());
    System.out.println(bookV1);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * 4: 424
     */

    System.out.println("UnknownFields: " + bookV1.getUnknownFields());
    /**
     * UnknownFields: 4: 424
     */
  }

  private static void toVersion2FromVersionWhereNameOfFieldIsChanged() throws InvalidProtocolBufferException {
    System.out.println("-------------fromVersionWhenNameOfFieldIsChangedToVersion2-------------");
    Book_WhenNameOfFieldIsChanged bookWhenNameOfFieldIsChanged = Book_WhenNameOfFieldIsChanged.newBuilder()
        .setTitle("Clean Code")
        .setAuthor("Robert C. Martin")
        //.setPublisher("Helion") // <- Publisher was deleted
        .setLanguage("english")
        .build();

    System.out.println(bookWhenNameOfFieldIsChanged);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * language: "english"
     */

    Book_V2 bookV2 = Book_V2.parseFrom(bookWhenNameOfFieldIsChanged.toByteArray());
    System.out.println(bookV2);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * publisher: "english"
     */

  }

  private static void toVersion2FromVersionWhenTypeOfFieldIsChanged() throws InvalidProtocolBufferException {
    System.out.println("-------------fromVersionWhenTypeOfFieldIsChangedToVersion2-------------");
    Book_WhenTypeOfFieldIsChanged bookWhenTypeOfFieldIsChanged = Book_WhenTypeOfFieldIsChanged.newBuilder()
        .setTitle("Clean Code")
        .setAuthor("Robert C. Martin")
        //.setPublisher("Helion") // <- Publisher was deleted
        .setPages(424)
        .build();

    System.out.println(bookWhenTypeOfFieldIsChanged);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * pages: 424
     */

    Book_V2 bookV2 = Book_V2.parseFrom(bookWhenTypeOfFieldIsChanged.toByteArray());
    System.out.println(bookV2);
    /**
     * author: "Robert C. Martin"
     * title: "Clean Code"
     * 3: 424
     */

    System.out.println("UnknownFields: " + bookV2.getUnknownFields());
    /**
     * UnknownFields: 3: 424
     */
  }

}
