package com.darabi.testCustomView.model

sealed class RestrictionMode {

    abstract val timeout: Long

    object Foreground : RestrictionMode() {
        override val timeout: Long
            get() = 3000L
    }

    object Background : RestrictionMode() {
        override val timeout: Long
            get() = 3000L
    }
}