/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package GraddleApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        System.out.println("START GraddleApp test");
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}