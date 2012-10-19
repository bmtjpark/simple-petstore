package org.testinfected.petstore.decoration;

import java.io.IOException;
import java.io.Writer;

public interface Decorator {

    void decorate(Writer out, String content) throws IOException;
}
