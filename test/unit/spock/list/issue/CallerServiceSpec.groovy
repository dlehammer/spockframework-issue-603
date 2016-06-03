package spock.list.issue

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestFor(CallerService)
@TestMixin(GrailsUnitTestMixin)
class CallerServiceSpec extends Specification {

    ListService listService

    void setup() {
        listService = Mock(ListService)

        service.listService = listService
    }

    void "test list of numbers argument"() {
        given:
            Long number = 1L

        when:
            service.serviceMethod(number)

        then:
            1 * listService.serviceMethod(_ as List) >> { List numbers ->
                assert numbers == [number] // assertion is expected to be true - but fails @see https://github.com/spockframework/spock/issues/603

                return null
            }
            0 * _._ // no (more) method call on any mock
    }
}
