package org.mustangproject.server.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileService {

  private static final Logger LOGGER = LogManager.getLogger(FileService.class);

  public static File multipartToTempFile(MultipartFile inFile) throws IOException {

    final File tempFile = File.createTempFile("tovalidate" + UUID.randomUUID(), "mustangserver");
    try {
      FileOutputStream out = new FileOutputStream(tempFile);
      inFile.getInputStream().transferTo(out);
      LOGGER.debug("Created, temp file " + tempFile.getAbsolutePath());
      tempFile.deleteOnExit();
    } catch (Exception e) {
      LOGGER.error("Exception", e);
    }
    return tempFile;
  }

  public static File createTempFile(String in) throws IOException {
    final File tempFile = File.createTempFile("tovalidate" + UUID.randomUUID(), "mustangserver");
    try {
      Files.write(tempFile.toPath(), in.getBytes(StandardCharsets.UTF_8));
      LOGGER.debug("Created, temp file " + tempFile.getAbsolutePath());
      tempFile.deleteOnExit();
    } catch (Exception e) {
      LOGGER.error("Exception", e);
    }
    return tempFile;
  }

  public static InputStream multipartToStream(MultipartFile inFile) throws IOException {

    final File tempFile = File.createTempFile("tovalidate" + UUID.randomUUID(), "mustangserver");
    try {
      FileOutputStream out = new FileOutputStream(tempFile);
      inFile.getInputStream().transferTo(out);
      LOGGER.debug("Created, temp file " + tempFile.getAbsolutePath());
      tempFile.deleteOnExit();

    } catch (Exception e) {
      LOGGER.error("Exception", e);
    }
    return new FileInputStream(tempFile);
  }
}
