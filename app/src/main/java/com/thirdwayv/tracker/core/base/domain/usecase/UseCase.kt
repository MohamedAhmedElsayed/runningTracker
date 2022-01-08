package com.thirdwayv.tracker.core.base.domain.usecase


interface UseCase<I, O> {
    fun execute(input: I? = null): O
}
