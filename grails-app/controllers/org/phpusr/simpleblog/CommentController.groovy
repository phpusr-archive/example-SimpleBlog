package org.phpusr.simpleblog

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class CommentController {

    def listAjax(Long articleId) {
        println(params)
        def comments = Comment.findAll {
            article.id == articleId
        }

        render(contentType: "text/json") {
            array {
                for (comment in comments) {
                    c = {
                        body = comment.body
                        userId = comment.userId
                        userName = comment.user.username
                    }
                }
            }
        }
    }

}
