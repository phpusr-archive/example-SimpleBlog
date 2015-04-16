package org.phpusr.simpleblog

/**
 * Комментарий к статье
 */
class Comment {

    static constraints = {
        article()
        user()
        body blank: false, maxSize: 255
    }

    Article article
    User user
    String body

}
