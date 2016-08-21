package com.ziomacki.github.search.model

import android.os.Bundle
import spock.lang.Specification

class SearchSavedInstanceHelperSpec extends Specification {
    def "save query and isSearchMade flag"() {
        given:
            Bundle outStateMock = Mock(Bundle)
            String query = "test"
            boolean isSearchMade = true
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            sut.saveInstance(outStateMock, query, isSearchMade)
        then:
            1 * outStateMock.putBoolean("KEY_IS_SEARCH_MADE", isSearchMade)
            1 * outStateMock.putString("KEY_QUERY", query)
    }

    def "read empty query if one is not stored"() {
        given:
            Bundle saveInstanceStub = Stub(Bundle)
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            String query = sut.getQuery(saveInstanceStub)
        then:
            query.equals("")
    }

    def "read query if one is stored"() {
        given:
            Bundle saveInstanceStub = Stub(Bundle)
            saveInstanceStub.getString("KEY_QUERY", "") >> "result"
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            String query = sut.getQuery(saveInstanceStub)
        then:
            query.equals("result")
    }

    def "read search not made if isSearchMade flag is not stored"() {
        given:
            Bundle saveInstanceStub = Stub(Bundle)
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            boolean flag = sut.isSearchMade(saveInstanceStub)
        then:
            flag == false
    }

    def "read true isSearchMade if isSearchMade flag true is stored"() {
        given:
            Bundle saveInstanceStub = Stub(Bundle)
            saveInstanceStub.getBoolean("KEY_IS_SEARCH_MADE", false) >> true
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            boolean flag = sut.isSearchMade(saveInstanceStub)
        then:
            flag == true
    }

    def "read false isSearchMade if savedInstance is null"() {
        given:
            Bundle saveInstanceNull = null
            SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            boolean flag = sut.isSearchMade(saveInstanceNull)
        then:
            flag == false
    }

    def "read empty queryif savedInstance is null"() {
        given:
        Bundle savedInstanceNull = null
        SearchSavedInstanceHelper sut = new SearchSavedInstanceHelper()
        when:
            String query = sut.getQuery(savedInstanceNull)
        then:
            query.equals("")
    }
}
