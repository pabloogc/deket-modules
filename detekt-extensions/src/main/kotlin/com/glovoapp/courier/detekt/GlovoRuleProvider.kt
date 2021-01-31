package com.glovoapp.courier.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class GlovoRuleProvider : RuleSetProvider {

    override val ruleSetId: String = "glovo"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                ModuleImportRule()
            )
        )
    }
}
