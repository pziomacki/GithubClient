package com.ziomacki.github.user.model

import rx.Observable
import rx.observers.TestSubscriber
import spock.lang.Specification

class UserFetchSpec extends Specification {
    def "store user after successful response"() {
        given:
            User user = new User();
            user.isAllDataFetched = false
            UserService userServiceStub = Stub(UserService)
            UserRepository userRepositoryMock = Mock(UserRepository)
            userServiceStub.fetchUser("test") >> Observable.just(user)
            UserFetch sut = new UserFetch(userServiceStub, userRepositoryMock)
            TestSubscriber testSubscriber = new TestSubscriber()
        when:
            sut.fetchUser("test").subscribe(testSubscriber)
        then:
            1 * userRepositoryMock.copyOrUpdateUser(user)
            user.isAllDataFetched == true
    }

    def "don't try to store response if service return error"() {
        given:
            UserService userServiceStub = Stub(UserService)
            UserRepository userRepositoryMock = Mock(UserRepository)
            Exception exception = new Exception()
            userServiceStub.fetchUser("") >> Observable.error(exception)
            UserFetch sut = new UserFetch(userServiceStub, userRepositoryMock)
            TestSubscriber testSubscriber = new TestSubscriber()
        when:
            sut.fetchUser("").subscribe(testSubscriber)
        then:
            testSubscriber.assertError(exception)
            0 * userRepositoryMock.copyOrUpdateUser(_ as User)
    }
}
