package spock.list.issue

class CallerService {

    ListService listService

    def serviceMethod(Long number) {
        listService.serviceMethod([number])
    }

    def serviceMethodDebugFriendly(Long number) {
        List<Long> numbers = [number]
        listService.serviceMethod(numbers)
    }
}
