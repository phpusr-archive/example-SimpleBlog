package org.phpusr.simpleblog

/**
 * Статья
 */
class Article {

    static constraints = {
        user()
        title blank: false, maxSize: 100
        body blank: false, maxSize: 2_147_483_647
    }

    User user
    String title
    String body

}
