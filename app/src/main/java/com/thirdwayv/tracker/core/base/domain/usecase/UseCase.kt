package com.thirdwayv.tracker.core.base.domain.usecase

/**
 * base use case
 * @param nullable I
 * output O
 * */
interface UseCase<I, O> {
    fun execute(input: I? = null): O
}
