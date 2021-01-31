@file:SuppressWarnings("ModuleImportRule")

package com.example.moduledemo.module2

import com.example.moduledemo.module1.internal.Module1InternalClass
import com.example.moduledemo.module2.internal.Module2InternalClass

fun main() {
    Module2InternalClass()
    Module1InternalClass()
}
