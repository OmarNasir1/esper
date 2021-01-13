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
package com.espertech.esper.common.client.configuration.compiler;

import com.espertech.esper.common.client.configuration.ConfigurationException;
import com.espertech.esper.common.client.util.EventTypeBusModifier;
import com.espertech.esper.common.client.util.NameAccessModifier;

import java.io.Serializable;

/**
 * Code generation settings.
 */
public class ConfigurationCompilerByteCode implements Serializable {
    private static final long serialVersionUID = -2067096962204046301L;

    private boolean includeDebugSymbols = false;
    private boolean includeComments = false;
    private boolean attachEPL = true;
    private boolean attachModuleEPL = false;
    private boolean attachPatternEPL = false;
    private boolean allowSubscriber = false;
    private boolean allowInlinedClass = true;
    private boolean instrumented;
    private NameAccessModifier accessModifierEventType = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierNamedWindow = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierContext = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierVariable = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierExpression = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierScript = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierTable = NameAccessModifier.PRIVATE;
    private NameAccessModifier accessModifierInlinedClass = NameAccessModifier.PRIVATE;
    private EventTypeBusModifier busModifierEventType = EventTypeBusModifier.NONBUS;
    private int threadPoolCompilerNumThreads = 8;
    private Integer threadPoolCompilerCapacity = null;
    private int maxMethodsPerClass = 1024; // JVM constant pool is 64k
    private int internalUseOnlyMaxMembersPerClass = 2 * 1024;
    private int internalUseOnlyMaxMethodComplexity = 1024; // JVM max method size is 64k, the default is a 1k complexity

    /**
     * Set all access modifiers to public.
     */
    public void setAccessModifiersPublic() {
        accessModifierEventType = NameAccessModifier.PUBLIC;
        accessModifierNamedWindow = NameAccessModifier.PUBLIC;
        accessModifierContext = NameAccessModifier.PUBLIC;
        accessModifierVariable = NameAccessModifier.PUBLIC;
        accessModifierExpression = NameAccessModifier.PUBLIC;
        accessModifierScript = NameAccessModifier.PUBLIC;
        accessModifierTable = NameAccessModifier.PUBLIC;
        accessModifierInlinedClass = NameAccessModifier.PUBLIC;
    }

    /**
     * Returns indicator whether the binary class code should include debug symbols
     *
     * @return indicator
     */
    public boolean isIncludeDebugSymbols() {
        return includeDebugSymbols;
    }

    /**
     * Sets indicator whether the binary class code should include debug symbols
     *
     * @param includeDebugSymbols indicator
     */
    public void setIncludeDebugSymbols(boolean includeDebugSymbols) {
        this.includeDebugSymbols = includeDebugSymbols;
    }

    /**
     * Returns indicator whether the generated source code should include comments for tracing back
     *
     * @return indicator
     */
    public boolean isIncludeComments() {
        return includeComments;
    }

    /**
     * Sets indicator whether the generated source code should include comments for tracing back
     *
     * @param includeComments indicator
     */
    public void setIncludeComments(boolean includeComments) {
        this.includeComments = includeComments;
    }

    /**
     * Returns the indicator whether the EPL text will be available as a statement property.
     * The default is true and the compiler provides the EPL as a statement property.
     * When set to false the compiler does not retain the EPL in the compiler output.
     *
     * @return indicator
     */
    public boolean isAttachEPL() {
        return attachEPL;
    }

    /**
     * Sets the indicator whether the EPL text will be available as a statement property.
     * The default is true and the compiler provides the EPL as a statement property.
     * When set to false the compiler does not retain the EPL in the compiler output.
     *
     * @param attachEPL indicator
     */
    public void setAttachEPL(boolean attachEPL) {
        this.attachEPL = attachEPL;
    }

    /**
     * Returns the indicator whether the EPL module text will be available as a module property.
     * The default is false and the compiler does not provide the module EPL as a module property.
     * When set to true the compiler retains the module EPL in the compiler output.
     *
     * @return indicator
     */
    public boolean isAttachModuleEPL() {
        return attachModuleEPL;
    }

    /**
     * Sets the indicator whether the EPL module text will be available as a module property.
     * The default is false and the compiler does not provide the module EPL as a module property.
     * When set to true the compiler retains the module EPL in the compiler output.
     *
     * @param enableAttachModuleEPL indicator
     */
    public void setAttachModuleEPL(boolean enableAttachModuleEPL) {
        this.attachModuleEPL = enableAttachModuleEPL;
    }

    /**
     * Returns indicator whether any statements allow subscribers or not (false by default).
     * The default is false which results in the runtime throwing an exception when an application calls {@code setSubscriber}
     * on a statement.
     *
     * @return indicator
     */
    public boolean isAllowSubscriber() {
        return allowSubscriber;
    }

    /**
     * Sets indicator whether any statements allow subscribers or not (false by default).
     * The default is false which results in the runtime throwing an exception when an application calls {@code setSubscriber}
     * on a statement.
     *
     * @param allowSubscriber indicator
     */
    public void setAllowSubscriber(boolean allowSubscriber) {
        this.allowSubscriber = allowSubscriber;
    }

    /**
     * Returns the indicator whether the compiler generates instrumented byte code for use with the debugger.
     *
     * @return indicator
     */
    public boolean isInstrumented() {
        return instrumented;
    }

    /**
     * Sets the indicator whether the compiler generates instrumented byte code for use with the debugger.
     *
     * @param instrumented indicator
     */
    public void setInstrumented(boolean instrumented) {
        this.instrumented = instrumented;
    }

    /**
     * Returns the default access modifier for event types
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierEventType() {
        return accessModifierEventType;
    }

    /**
     * Sets the default access modifier for event types
     *
     * @param accessModifierEventType access modifier
     */
    public void setAccessModifierEventType(NameAccessModifier accessModifierEventType) {
        checkModifier(accessModifierEventType);
        this.accessModifierEventType = accessModifierEventType;
    }

    /**
     * Returns the default access modifier for named windows
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierNamedWindow() {
        return accessModifierNamedWindow;
    }

    /**
     * Sets the default access modifier for named windows
     *
     * @param accessModifierNamedWindow access modifier
     */
    public void setAccessModifierNamedWindow(NameAccessModifier accessModifierNamedWindow) {
        checkModifier(accessModifierNamedWindow);
        this.accessModifierNamedWindow = accessModifierNamedWindow;
    }

    /**
     * Returns the default access modifier for contexts
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierContext() {
        return accessModifierContext;
    }

    /**
     * Sets the default access modifier for contexts
     *
     * @param accessModifierContext access modifier
     */
    public void setAccessModifierContext(NameAccessModifier accessModifierContext) {
        checkModifier(accessModifierContext);
        this.accessModifierContext = accessModifierContext;
    }

    /**
     * Returns the default access modifier for variables
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierVariable() {
        return accessModifierVariable;
    }

    /**
     * Sets the default access modifier for variables
     *
     * @param accessModifierVariable access modifier
     */
    public void setAccessModifierVariable(NameAccessModifier accessModifierVariable) {
        checkModifier(accessModifierVariable);
        this.accessModifierVariable = accessModifierVariable;
    }

    /**
     * Returns the default access modifier for declared expressions
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierExpression() {
        return accessModifierExpression;
    }

    /**
     * Sets the default access modifier for declared expressions
     *
     * @param accessModifierExpression access modifier
     */
    public void setAccessModifierExpression(NameAccessModifier accessModifierExpression) {
        checkModifier(accessModifierExpression);
        this.accessModifierExpression = accessModifierExpression;
    }

    /**
     * Returns the default access modifier for scripts
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierScript() {
        return accessModifierScript;
    }

    /**
     * Sets the default access modifier for scripts
     *
     * @param accessModifierScript bus modifier
     */
    public void setAccessModifierScript(NameAccessModifier accessModifierScript) {
        checkModifier(accessModifierScript);
        this.accessModifierScript = accessModifierScript;
    }

    /**
     * Returns the default access modifier for tables
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierTable() {
        return accessModifierTable;
    }

    /**
     * Sets the default access modifier for tables
     *
     * @param accessModifierTable bus modifier
     */
    public void setAccessModifierTable(NameAccessModifier accessModifierTable) {
        this.accessModifierTable = accessModifierTable;
    }

    /**
     * Returns the default access modifier for inlined-classes
     *
     * @return access modifier
     */
    public NameAccessModifier getAccessModifierInlinedClass() {
        return accessModifierInlinedClass;
    }

    /**
     * Sets the default access modifier for inlined-classes
     *
     * @param accessModifierInlinedClass bus modifier
     */
    public void setAccessModifierInlinedClass(NameAccessModifier accessModifierInlinedClass) {
        this.accessModifierInlinedClass = accessModifierInlinedClass;
    }

    /**
     * Returns the default bus modifier for event types
     *
     * @return access modifier
     */
    public EventTypeBusModifier getBusModifierEventType() {
        return busModifierEventType;
    }

    /**
     * Sets the default bus modifier for event types
     *
     * @param busModifierEventType bus modifier
     */
    public void setBusModifierEventType(EventTypeBusModifier busModifierEventType) {
        this.busModifierEventType = busModifierEventType;
    }

    /**
     * Returns the indicator whether, for tools with access to pattern factories, the pattern subexpression text
     * will be available for the pattern.
     * The default is false and the compiler does not produce text for patterns for tooling.
     * When set to true the compiler does generate pattern subexpression text for pattern for use by tools.
     *
     * @return indicator
     */
    public boolean isAttachPatternEPL() {
        return attachPatternEPL;
    }

    /**
     * Sets the indicator whether, for tools with access to pattern factories, the pattern subexpression text
     * will be available for the pattern.
     * The default is false and the compiler does not produce text for patterns for tooling.
     * When set to true the compiler does generate pattern subexpression text for pattern for use by tools.
     *
     * @param attachPatternEPL flag
     */
    public void setAttachPatternEPL(boolean attachPatternEPL) {
        this.attachPatternEPL = attachPatternEPL;
    }

    /**
     * Returns the number of threads available for parallel compilation of multiple EPL statements. The default is 8 threads.
     *
     * @return number of threads
     */
    public int getThreadPoolCompilerNumThreads() {
        return threadPoolCompilerNumThreads;
    }

    /**
     * Sets the number of threads available for parallel compilation of multiple EPL statements. The default is 8 threads.
     *
     * @param threadPoolCompilerNumThreads number of threads
     */
    public void setThreadPoolCompilerNumThreads(int threadPoolCompilerNumThreads) {
        this.threadPoolCompilerNumThreads = threadPoolCompilerNumThreads;
    }

    /**
     * Returns the capacity of the parallel compiler semaphore, or null if none defined (null is the default and is the unbounded case).
     *
     * @return capacity or null if none defined
     */
    public Integer getThreadPoolCompilerCapacity() {
        return threadPoolCompilerCapacity;
    }

    /**
     * Sets the capacity of the parallel compiler semaphore, or null if none defined (null is the default and is the unbounded case).
     *
     * @param threadPoolCompilerCapacity or null if none defined
     */
    public void setThreadPoolCompilerCapacity(Integer threadPoolCompilerCapacity) {
        this.threadPoolCompilerCapacity = threadPoolCompilerCapacity;
    }

    /**
     * Returns the maximum number of methods per class, which defaults to 1k. The lower limit for this number is 1000.
     *
     * @return max number methods per class
     */
    public int getMaxMethodsPerClass() {
        return maxMethodsPerClass;
    }

    /**
     * Sets the maximum number of methods per class, which defaults to 1k.
     *
     * @param maxMethodsPerClass max number methods per class
     */
    public void setMaxMethodsPerClass(int maxMethodsPerClass) {
        this.maxMethodsPerClass = maxMethodsPerClass;
    }

    /**
     * (Internal-use-only) Returns the maximum number of members per class, which defaults to 2k. The lower limit for this number is 1.
     *
     * @return max number of members per class
     */
    public int getInternalUseOnlyMaxMembersPerClass() {
        return internalUseOnlyMaxMembersPerClass;
    }

    /**
     * (Internal-use-only) Sets the maximum number of members per class, which defaults to 2k. The lower limit for this number is 1.
     *
     * @param internalUseOnlyMaxMembersPerClass max number of members per class
     */
    public void setInternalUseOnlyMaxMembersPerClass(int internalUseOnlyMaxMembersPerClass) {
        this.internalUseOnlyMaxMembersPerClass = internalUseOnlyMaxMembersPerClass;
    }

    /**
     * (Internal-use-only) Sets the maximum method complexity, which defaults to 1k. Applicable to methods that repeat operations on elements.
     * This roughly corresponds to lines of code of a method. The lower limit is not defined.
     *
     * @return max method complexity
     */
    public int getInternalUseOnlyMaxMethodComplexity() {
        return internalUseOnlyMaxMethodComplexity;
    }

    /**
     * (Internal-use-only) Sets the maximum method complexity, which defaults to 1k. Applicable to methods that repeat operations on elements.
     * This roughly corresponds to lines of code of a method. The lower limit is not defined.
     *
     * @param internalUseOnlyMaxMethodComplexity max method complexity
     */
    public void setInternalUseOnlyMaxMethodComplexity(int internalUseOnlyMaxMethodComplexity) {
        this.internalUseOnlyMaxMethodComplexity = internalUseOnlyMaxMethodComplexity;
    }

    /**
     * Returns the flag whether the compiler allows inlined classes
     * @return flag
     */
    public boolean isAllowInlinedClass() {
        return allowInlinedClass;
    }

    /**
     * Sets the flag whether the compiler allows inlined classes
     * @param allowInlinedClass flag
     */
    public void setAllowInlinedClass(boolean allowInlinedClass) {
        this.allowInlinedClass = allowInlinedClass;
    }

    private void checkModifier(NameAccessModifier modifier) {
        if (!modifier.isModuleProvidedAccessModifier()) {
            throw new ConfigurationException("Access modifier configuration allows private, protected or public");
        }
    }
}
