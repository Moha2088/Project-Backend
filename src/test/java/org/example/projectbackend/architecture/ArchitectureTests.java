package org.example.projectbackend.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "org.example.projectbackend")
public class ArchitectureTests {
    
    @ArchTest
    static final ArchRule controllerShouldDependOnServiceClasses = classes()
            .that()
            .resideInAPackage("org.example.projectbackend.controller")
            .should().accessClassesThat().resideInAPackage("org.example.projectbackend.services..")
            .because("Controllers should be dependent on the service layer");
    
    @ArchTest
    static final ArchRule controllerShouldNotDependOnRepositories = noClasses()
            .that()
            .resideInAPackage("org.example.projectbackend.controller")
            .should().accessClassesThat()
            .resideInAPackage("org.example.projectbackend.repositories")
            .because("Controllers shouldn't be dependent on repositories");
}