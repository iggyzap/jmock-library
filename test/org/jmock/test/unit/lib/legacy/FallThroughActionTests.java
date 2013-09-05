package org.jmock.test.unit.lib.legacy;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import static org.jmock.lib.legacy.FallThroughAction.fallThrough;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class FallThroughActionTests {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    {
        context.setImposteriser(ClassImposteriser.INSTANCE);
    }

    static class Foo {

        public String someFoo() {
            return "1" + "2";
        }

        public String callFoo() {
            return someFoo();
        }

        public String fooAndParam (String bar) {
            return bar + someFoo();
        }
    }

    @Mock
    private Foo mockedFoo;


    @Test
    public void testPartialFallThrough() {
        context.checking(new Expectations() {{
            oneOf(mockedFoo).callFoo();
            will(fallThrough());
            oneOf(mockedFoo).someFoo();
            will(returnValue("66"));
        }});

        assertEquals("66", mockedFoo.callFoo());
    }

    @Test
    public void testCompleteFallThrough() {
        context.checking(new Expectations() {{
            oneOf(mockedFoo).callFoo();
            will(fallThrough());
            oneOf(mockedFoo).someFoo();
            will(fallThrough());
        }});

        assertEquals("12", mockedFoo.callFoo());
    }


    @Test
    public void testParamFallThrough() {
        context.checking(new Expectations() {{
            oneOf(mockedFoo).fooAndParam("11");
            will(fallThrough());
            oneOf(mockedFoo).someFoo();
            will(fallThrough());
        }});

        assertEquals("1112", mockedFoo.fooAndParam("11"));
    }
}
