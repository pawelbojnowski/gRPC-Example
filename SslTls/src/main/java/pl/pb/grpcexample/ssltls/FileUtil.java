package pl.pb.grpcexample.ssltls;

import java.io.File;

public class FileUtil {

  public static File loadFile(String filename) {
    try {
      ClassLoader classLoader = MainServer.class.getClassLoader();
      return new File(classLoader.getResource(filename).getFile());
    } catch (Exception e) {
      throw new IllegalArgumentException("Missing '" + filename + "' files. Please run commands from 'SSL_TLS/createCert.txt'");
    }
  }
}
