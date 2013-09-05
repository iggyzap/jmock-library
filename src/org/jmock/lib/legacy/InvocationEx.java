package org.jmock.lib.legacy;

import net.sf.cglib.proxy.MethodProxy;
import org.jmock.api.Invocation;

import java.lang.reflect.Method;

/**
 * Invocation extension that enables fall-through logic
 */
public class InvocationEx extends Invocation{

    private final MethodProxy fallThrough;

    public InvocationEx(MethodProxy fallThrough, Object invoked, Method method, Object... parameterValues) {
        super(invoked, method, parameterValues);
        this.fallThrough = fallThrough;
    }

    public MethodProxy getFallThrough() {
        return fallThrough;
    }
}
