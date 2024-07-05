package com.revature.ams.util;

import java.util.Scanner;

// Java 8 Feature that allows us to define single implementations for methods
// Used for one off operations that may vary from class to class
@FunctionalInterface
public interface ScannerValidator {
    boolean isValid(Scanner scanner, String errorMessage); // There is no implementation, we define at assignment
}
