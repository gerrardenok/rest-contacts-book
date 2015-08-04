package org.itechart.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence sequence) {
        return sequence.toString();
    }

    @Override
    public boolean matches(CharSequence sequence, String s) {
        return sequence.toString().equals(s);
    }
}
