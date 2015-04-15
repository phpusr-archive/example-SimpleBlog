package org.phpusr.simpleblog

class UserTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = 'sec'

    def userService

    /**
     * Выводит содержимое если оно принадлежит автору
     */
    def ifAuthor = { attrs, body ->
        def userId = attrs.userId as Long
        if (userId == userService.user.id) out << body()
    }

}
