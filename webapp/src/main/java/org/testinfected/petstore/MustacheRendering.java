package org.testinfected.petstore;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.testinfected.petstore.util.Streams;

import java.io.IOException;
import java.io.Reader;

public class MustacheRendering implements Renderer {

    private static final String TEMPLATE_EXTENSION = ".html";

    private ResourceLoader resourceLoader;
    private final Mustache.Compiler mustache;

    public MustacheRendering(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.mustache = Mustache.compiler();
    }

    public String render(String templateName, Object context) throws IOException {
        Reader source = null;
        try {
            source = load(templateName);
            Template template = mustache.withLoader(new TemplateLoader()).compile(source);
            return template.execute(context);
        } finally {
            Streams.close(source);
        }
    }

    private Reader load(String templateName) throws IOException {
        Resource template = resourceLoader.load(templateFile(templateName));
        return template.read();
    }

    private String templateFile(String templateName) {
        return templateName + TEMPLATE_EXTENSION;
    }

    private class TemplateLoader implements Mustache.TemplateLoader {
        public Reader getTemplate(String name) throws Exception {
            return load(name);
        }
    }
}