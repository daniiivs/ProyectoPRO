package Tests;

import UI.UtilityMethods;

import static org.junit.jupiter.api.Assertions.*;

class UtilityMethodsTest {
    @org.junit.jupiter.api.Test
    void checkDniReal() {
        String dni = "54136461G";
        assertTrue(UtilityMethods.checkDni(dni));
    }

    @org.junit.jupiter.api.Test
    void checkDniShort() {
        String dni = "5413646G";
        assertFalse(UtilityMethods.checkDni(dni));
    }

    @org.junit.jupiter.api.Test
    void checkDniLong() {
        String dni = "541364612G";
        assertFalse(UtilityMethods.checkDni(dni));
    }

    @org.junit.jupiter.api.Test
    void checkDniWrongLetter() {
        String dni = "54136461H";
        assertFalse(UtilityMethods.checkDni(dni));
    }

    @org.junit.jupiter.api.Test
    void checkDniNoLetter() {
        String dni = "54136461";
        assertFalse(UtilityMethods.checkDni(dni));
    }

    @org.junit.jupiter.api.Test
    void checkMatchSameWord() {
        String A = "Hello";
        String B = "Hello";
        assertTrue(UtilityMethods.checkMatch(A, B));
    }

    @org.junit.jupiter.api.Test
    void checkMatchDifferentWord() {
        String A = "Hello";
        String B = "hello";
        assertFalse(UtilityMethods.checkMatch(A, B));
    }
}