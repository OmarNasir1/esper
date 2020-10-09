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
package com.espertech.esper.common.internal.epl.agg.core;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.client.type.EPTypeClass;
import com.espertech.esper.common.internal.epl.expression.core.ExprEvaluatorContext;

import java.util.Collection;

public interface AggregationRow {
    EPTypeClass EPTYPE = new EPTypeClass(AggregationRow.class);

    void applyEnter(EventBean[] eventsPerStream, ExprEvaluatorContext exprEvaluatorContext);

    void applyLeave(EventBean[] eventsPerStream, ExprEvaluatorContext exprEvaluatorContext);

    void enterAgg(int scol, Object value);

    void leaveAgg(int scol, Object value);

    void enterAccess(int scol, EventBean[] eventsPerStream, ExprEvaluatorContext exprEvaluatorContext);

    void leaveAccess(int scol, EventBean[] eventsPerStream, ExprEvaluatorContext exprEvaluatorContext);

    Object getAccessState(int scol);

    void clear();

    Object getValue(int vcol, EventBean[] eventsPerStream, boolean isNewData, ExprEvaluatorContext exprEvaluatorContext);

    Collection<EventBean> getCollectionOfEvents(int vcol, EventBean[] eventsPerStream, boolean isNewData, ExprEvaluatorContext context);

    EventBean getEventBean(int vcol, EventBean[] eventsPerStream, boolean isNewData, ExprEvaluatorContext context);

    Collection<Object> getCollectionScalar(int vcol, EventBean[] eventsPerStream, boolean isNewData, ExprEvaluatorContext context);

    void increaseRefcount();

    void decreaseRefcount();

    long getRefcount();

    long getLastUpdateTime();

    void setLastUpdateTime(long currentTime);

    void reset(int column);
}
