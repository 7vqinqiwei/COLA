package com.huawei.charging;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class CleanArchTest {
    @Test
    public void protect_clean_arch() {
        JavaClasses classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.huawei.charging");

        layeredArchitecture()
                .layer("adapter").definedBy("com.huawei.charging.adapter")
                .layer("application").definedBy("com.huawei.charging.application")
                .layer("domain").definedBy("com.huawei.charging.domain")
                .layer("infrastructure").definedBy("com.huawei.charging.infrastructure")
                .whereLayer("adapter").mayNotBeAccessedByAnyLayer()
                //.whereLayer("domain").mayOnlyBeAccessedByLayers("application", "infrastructure")
                .as("The layer dependencies must be respected")
                .because("we must follow the Clean Architecture principle")
                .check(classes);
    }
}
