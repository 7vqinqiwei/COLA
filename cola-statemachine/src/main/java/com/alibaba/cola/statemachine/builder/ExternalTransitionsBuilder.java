package com.alibaba.cola.statemachine.builder;

/**
 * ExternalTransitionsBuilder
 *
 * This builder is for multiple transitions, currently only support multiple sources <----> one target
 *
 * @author Frank Zhang
 * @date 2020-02-08 7:41 PM
 */
public interface ExternalTransitionsBuilder<S, E, C> {
    /**
     * ExternalTransitionsBuilder
     * @param stateIds
     * @return
     */
    From<S, E, C> fromAmong(S... stateIds);
}
