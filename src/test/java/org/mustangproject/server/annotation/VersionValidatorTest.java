package org.mustangproject.server.annotation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@WebMvcTest(VersionValidator.class)
@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
public class VersionValidatorTest {

    private final Validator validator;

    public VersionValidatorTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidVersion() {
        class TestClass {
            @ValidVersion
            Integer version;

            TestClass(Integer version) {
                this.version = version;
            }
        }

        assertTrue(validator.validate(new TestClass(1)).isEmpty());
        assertFalse(validator.validate(new TestClass(3)).isEmpty());
    }
}
