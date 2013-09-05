package org.jmock.lib.legacy;

import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;

/**
 * This action enables fall-through as custom action for legacy code, enabling extracting series of methods
 * out of the class
 */
public class FallThroughAction extends CustomAction{

    public static FallThroughAction INSTANCE = new FallThroughAction();

    public static Action fallThrough() {
        return INSTANCE;
    }

    public FallThroughAction() {
        super("Calls original method");
    }

    @Override
    public Object invoke(Invocation invocation) throws Throwable {
        if (invocation instanceof InvocationEx) {
            InvocationEx ex = (InvocationEx) invocation;
            return ex.getFallThrough().invokeSuper(ex.getInvokedObject(), ex.getParametersAsArray());
        }
        throw new UnsupportedOperationException(String.format("Please use this action with %1$s imposteriser", ClassImposteriser.class.getName()));
    }
}
