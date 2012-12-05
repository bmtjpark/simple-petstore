package test.unit.org.testinfected.support.decoration;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.simpleframework.http.Response;
import org.simpleframework.http.ResponseWrapper;
import org.simpleframework.http.Status;
import org.testinfected.support.decoration.HtmlPageSelector;
import org.testinfected.support.decoration.Selector;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.simpleframework.http.Status.OK;

public class HtmlPageSelectorTest {
    Mockery context = new JUnit4Mockery();

    Selector selector = new HtmlPageSelector();
    ResponseStub response = new ResponseStub();

    @Test public void
    selectsHtmlContent() throws IOException {
        response.setCode(OK.getCode());
        response.setContentType("text/html; charset=iso-8859-1");
        assertThat("content selection", selector.select(response), equalTo(true));
    }

    @Test public void
    doesNotSelectContentWhenStatusNotOK() throws IOException {
        response.setCode(Status.SEE_OTHER.getCode());
        assertThat("content selection", selector.select(response), equalTo(false));
    }

    @Test public void
    doesNotSelectContentIfNotHtml() throws IOException {
        response.setCode(OK.getCode());
        response.setContentType("text/plain");
        assertThat("content selection", selector.select(response), equalTo(false));
    }

    @Test public void
    doesNotSelectResponseWithoutContentType() throws IOException {
        response.setCode(OK.getCode());
        assertThat("content selection", selector.select(response), equalTo(false));
    }

    public class ResponseStub extends ResponseWrapper {

        private int code;
        private String contentType;

        public ResponseStub() {
            super(context.mock(Response.class));
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public int getCode() {
            return code;
        }

        public String getValue(String name) {
            return "Content-Type".equals(name) ? contentType : null;
        }
    }
}