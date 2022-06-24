package com.greenlabs.day;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class syntaxTest {

    @Test
    void test() {
        Optional<String> op = Optional.empty();
        Assertions.assertThat(op.isPresent()).isEqualTo(false);

        op = Optional.ofNullable(null);
        Assertions.assertThat(op.isPresent()).isEqualTo(false);
    }
}
