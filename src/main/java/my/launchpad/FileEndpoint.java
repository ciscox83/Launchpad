package my.launchpad;

public class FileEndpoint {

  private final String directoryPath;
  private final String filename;

  public FileEndpoint(String directoryPath, String filename) {
    this.directoryPath = directoryPath;
    this.filename = filename;
  }

  public String asString() {
    return "file://" + directoryPath + "?fileName=" + filename;
  }
}
