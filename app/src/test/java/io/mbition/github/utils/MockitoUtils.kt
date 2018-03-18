package io.mbition.github.utils

import com.nhaarman.mockito_kotlin.given
import org.mockito.BDDMockito

/**
 * Created by Tohamy on 3/15/2018.
 */
infix fun <T> T?.willReturn(value: T): BDDMockito.BDDMyOngoingStubbing<T?> =
        given(this).willReturn(value)