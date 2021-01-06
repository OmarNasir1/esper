/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.regressionlib.suite.epl.fromclausemethod;

import com.espertech.esper.common.internal.support.SupportBean;
import com.espertech.esper.regressionlib.framework.RegressionEnvironment;
import com.espertech.esper.regressionlib.framework.RegressionExecution;
import com.espertech.esper.regressionlib.support.epl.SupportStaticMethodInvocations;

import static org.junit.Assert.assertEquals;

public class EPLFromClauseMethodCacheLRU implements RegressionExecution {

    public void run(RegressionEnvironment env) {
        String joinStatement = "@name('s0') select id, p00, theString from " +
            "SupportBean#length(100) as s1, " +
            " method:SupportStaticMethodInvocations.fetchObjectLog(theString, intPrimitive)";
        env.compileDeploy(joinStatement).addListener("s0");

        // set sleep off
        SupportStaticMethodInvocations.getInvocationSizeReset();

        // The LRU cache caches per same keys
        String[] fields = new String[]{"id", "p00", "theString"};
        sendBeanEvent(env, "E1", 1);
        env.assertPropsListenerNew("s0", fields, new Object[]{1, "|E1|", "E1"});

        sendBeanEvent(env, "E2", 2);
        env.assertPropsListenerNew("s0", fields, new Object[]{2, "|E2|", "E2"});

        sendBeanEvent(env, "E3", 3);
        env.assertPropsListenerNew("s0", fields, new Object[]{3, "|E3|", "E3"});
        assertEquals(3, SupportStaticMethodInvocations.getInvocationSizeReset());

        // should be cached
        sendBeanEvent(env, "E3", 3);
        env.assertPropsListenerNew("s0", fields, new Object[]{3, "|E3|", "E3"});
        assertEquals(0, SupportStaticMethodInvocations.getInvocationSizeReset());

        // should not be cached
        sendBeanEvent(env, "E4", 4);
        env.assertPropsListenerNew("s0", fields, new Object[]{4, "|E4|", "E4"});
        assertEquals(1, SupportStaticMethodInvocations.getInvocationSizeReset());

        // should be cached
        sendBeanEvent(env, "E2", 2);
        env.assertPropsListenerNew("s0", fields, new Object[]{2, "|E2|", "E2"});
        assertEquals(0, SupportStaticMethodInvocations.getInvocationSizeReset());

        // should not be cached
        sendBeanEvent(env, "E1", 1);
        env.assertPropsListenerNew("s0", fields, new Object[]{1, "|E1|", "E1"});
        assertEquals(1, SupportStaticMethodInvocations.getInvocationSizeReset());

        env.undeployAll();
    }

    private static void sendBeanEvent(RegressionEnvironment env, String theString, int intPrimitive) {
        SupportBean bean = new SupportBean();
        bean.setTheString(theString);
        bean.setIntPrimitive(intPrimitive);
        env.sendEventBean(bean);
    }
}
