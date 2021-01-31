package com.glovoapp.courier.detekt

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtPackageDirective


class ModuleImportRule : Rule() {

    override val issue = Issue(
        javaClass.simpleName,
        Severity.Defect,
        "This rule prevents importing internal classes from other modules.",
        Debt.FIVE_MINS
    )

    private var packageName = ""
    private var imports: List<KtImportDirective> = emptyList()

    override fun visitPackageDirective(directive: KtPackageDirective) {
        super.visitPackageDirective(directive)
        packageName = directive.qualifiedName
    }

    override fun visitImportList(importList: KtImportList) {
        super.visitImportList(importList)
        imports = importList.imports.toList()
    }

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)

        for (import in imports) {
            val path = import.importPath?.pathStr ?: ""
            if (!path.contains(".internal")) continue

            //It's a subpackage of the module, it's ok to import internals
            if (path.startsWith(packageName)) continue
            report(
                CodeSmell(
                    issue = issue,
                    entity = Entity.from(import),
                    message = "Importing internal package ${import.importPath?.pathStr}"
                )
            )
        }


        packageName = ""
        imports = emptyList()
    }
}