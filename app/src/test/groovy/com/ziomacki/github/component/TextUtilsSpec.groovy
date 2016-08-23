package com.ziomacki.github.component

import spock.lang.Specification

class TextUtilsSpec extends Specification {

    def "isNotEmpty return true for non empty string"() {
        given:
            String text = "test"
            TextUtils sut = new TextUtils()
        when:
            boolean isNotEmpty = sut.isNotEmpty(text)
        then:
            isNotEmpty == true
    }

    def "isNotEmpty return false for empty string"() {
        given:
            String text = ""
            TextUtils sut = new TextUtils()
        when:
            boolean isNotEmpty = sut.isNotEmpty(text)
        then:
            isNotEmpty == false
    }

    def "isNotEmpty return false for null string"() {
        given:
            String text = null
            TextUtils sut = new TextUtils()
        when:
            boolean isNotEmpty = sut.isNotEmpty(text)
        then:
            isNotEmpty == false
    }

}
