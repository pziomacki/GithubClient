package com.ziomacki.github.component

import android.content.Context
import spock.lang.Specification

class ResourceProviderSpec extends Specification {

    def "load drawable from resources"() {
        given:
            Context contextMock = Mock(Context)
            ResourceProvider sut = new ResourceProvider(contextMock)
        when:
            sut.getDrawable(0)
        then:
            1 * contextMock.getDrawable(0)
    }
}
