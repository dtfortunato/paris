package com.airbnb.paris.test

import com.airbnb.paris.processor.ParisProcessor
import com.google.common.truth.Truth.assert_
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourceSubjectFactory.javaSource
import org.junit.Test



class ParisProcessorTest {

    fun assertCase(folder: String) {
        val view = JavaFileObjects.forResource("$folder/MyView.java")
        val generatedParisClass = JavaFileObjects.forResource("$folder/Paris.java")
        val generatedStyleApplierClass = JavaFileObjects.forResource("$folder/MyViewStyleApplier.java")

        assert_().about(javaSource())
                .that(view)
                .processedWith(ParisProcessor())
                .compilesWithoutError()
                .and()
                .generatesSources(generatedParisClass)
                .and()
                .generatesSources(generatedStyleApplierClass)
    }

    @Test
    fun attributeAndDependency() {
        assertCase("attr_dependency")
    }

    @Test
    fun default() {
        assertCase("default")
    }

    @Test
    fun fields() {
        assertCase("fields")
    }

    @Test
    fun methods() {
        assertCase("methods")
    }

    @Test
    fun noAttributesAndDependency() {
        assertCase("no_attrs_dependency")
    }

    @Test
    fun styles() {
        assertCase("styles")
    }
}