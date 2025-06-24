package org.mustangproject.server.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {

    public static MockMultipartFile getMockMultipartFile(String fileName) throws Exception {

        FileInputStream inputFile = new FileInputStream(Objects.requireNonNull(TestUtils.class.getResource(fileName)).getFile());
        assertNotNull(inputFile);
        MockMultipartFile file = new MockMultipartFile("file", fileName, "multipart/form-data", inputFile);
        assertNotNull(file);
        return  file;
    }

    public static String getTestFileAsString(String fileName) throws IOException {
        String result = IOUtils.toString(
                TestUtils.class.getResourceAsStream(fileName),
                "UTF-8"
        );
        assertNotNull(result);
        return result;
    }

    public static byte[] getTestFileAsBytes(String fileName) throws IOException {
        String result = getTestFileAsString(fileName);
        return result.getBytes(StandardCharsets.UTF_8);
    }

}
