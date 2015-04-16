package org.phpusr.simpleblog

class UserTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = 'sec'

    def userService

    /**
     * Выводит скрытое поле с id текущего пользователя
     * @attr name REQUIRED the field name
     */
    def hiddenId = { attrs, body ->
        def userId = userService.user?.id
        out << g.hiddenField(name: attrs.name, value: userId)
    }

    /**
     * Выводит содержимое если оно принадлежит автору
     * @attr userId REQUIRED the field userId
     */
    def ifAuthor = { attrs, body ->
        def userId = attrs.userId as Long
        if (userId == userService.user?.id) out << body()
    }

}
