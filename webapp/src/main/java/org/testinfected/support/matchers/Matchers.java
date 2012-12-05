package org.testinfected.support.matchers;

import org.simpleframework.http.Path;
import org.simpleframework.http.Request;
import org.testinfected.support.Matcher;

public final class Matchers {

    public static <T> Matcher<T> equalTo(T other) {
        return IsEqual.equalTo(other);
    }

    public static Matcher<Request> anyRequest() {
        return new Anything<Request>();
    }

    public static Matcher<String> anyPath() {
        return new Anything<String>();
    }

    public static Matcher<String> anyMethod() {
        return new Anything<String>();
    }

    public static Matcher<Request> hasPath(Matcher<? super Path> path) {
        return RequestHasPath.hasPath(path);
    }

    public static Matcher<Request> hasNormalizedPath(Matcher<? super String> path) {
        return RequestHasPath.hasNormalizedPath(path);
    }

    public static Matcher<String> startingWith(String prefix) {
        return StartingWith.startingWith(prefix);
    }

    public static Matcher<Request> hasMethod(Matcher<? super String> method) {
        return RequestHasMethod.hasMethod(method);
    }

    public static Matcher<Request> hasMethod(String method) {
        return RequestHasMethod.hasMethod(method);
    }

    @SuppressWarnings("unchecked")
    public static <T> Matcher<T> allOf(Matcher<T>... matchers) {
        return AllOf.allOf(matchers);
    }

    private Matchers() {}
}