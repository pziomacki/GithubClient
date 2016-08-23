package com.ziomacki.github.user.model

import com.ziomacki.github.R
import com.ziomacki.github.search.eventbus.OnUserOpenEvent
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent
import spock.lang.Specification

class UserSpec extends Specification {

    def "getIconId returns user icon"() {
        given:
            User user = new User()
        when:
            int iconId = user.getItemIconId()
        then:
            iconId == R.drawable.ic_user
    }

    def "getSearchableItemOpenEvent returns OnUserOpenEvent with proper id"() {
        given:
            User user = new User()
            user.id = 12
        when:
            SearchableItemOpenEvent event = user.getSearchableItemOpenEvent()
        then:
            event instanceof OnUserOpenEvent
            OnUserOpenEvent onUserOpenEvent = (OnUserOpenEvent) event
            onUserOpenEvent.id == 12
    }
}
