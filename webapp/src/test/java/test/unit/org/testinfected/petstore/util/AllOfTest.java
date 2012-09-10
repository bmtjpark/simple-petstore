package test.unit.org.testinfected.petstore.util;

import org.junit.Test;
import org.testinfected.petstore.util.Anything;
import org.testinfected.petstore.util.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testinfected.petstore.util.AllOf.allOf;
import static org.testinfected.petstore.util.StartingWith.startingWith;

public class AllOfTest {

    @SuppressWarnings("unchecked")
    @Test public void
    evaluatesToLogicialConjunctionOfMultipleMatchers() {
        Matcher<String> allOf = allOf(startingWith("one"), startingWith("one two"), startingWith("one two three"));

        assertThat("no match but all matchers match", allOf.matches("one two three"));
        assertThat("match but one matcher does not match", !allOf.matches("one two two"));
    }

    @SuppressWarnings("unchecked")
    @Test public void
    supportsMatchesOnSuperType() {
        Matcher<String> allOf = allOf(startingWith("good"), new Anything<Object>());

        assertThat("match", allOf.matches("good"));
    }
}