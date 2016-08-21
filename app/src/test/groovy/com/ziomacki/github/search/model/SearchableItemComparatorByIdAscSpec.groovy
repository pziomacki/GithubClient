package com.ziomacki.github.search.model

import com.ziomacki.github.user.model.User
import spock.lang.Specification

class SearchableItemComparatorByIdAscSpec extends Specification {
    def "item1 has lower id than item2"() {
        given:
            User user1 = new User()
            user1.id = 2
            User user2 = new User()
            user2.id = 3
            SearchableItemComparatorByIdAsc sut = new SearchableItemComparatorByIdAsc()
        when:
            int result = sut.compare(user1, user2)
        then:
            assert result == -1
    }

    def "item1 has higher id than item2"() {
        given:
            User user1 = new User()
            user1.id = 3
            User user2 = new User()
            user2.id = 1
            SearchableItemComparatorByIdAsc sut = new SearchableItemComparatorByIdAsc()
        when:
            int result = sut.compare(user1, user2)
        then:
            result == 1
    }

    def "item1 has the same id as item2"() {
        given:
            User user1 = new User()
            user1.id = 1
            User user2 = new User()
            user2.id = 1
            SearchableItemComparatorByIdAsc sut = new SearchableItemComparatorByIdAsc()
        when:
            int result = sut.compare(user1, user2)
        then:
            result == 0
    }
}
