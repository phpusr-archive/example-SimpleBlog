package org.phpusr.simpleblog

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class CommentController {

    def userService

    def listAjax(Long articleId) {
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

    def addAjax(Comment commentInstance) {
        println(params)
        commentInstance.user = userService.user
        commentInstance.save(flush: true)


        def response = [status: 'OK']
        render response as JSON
    }

}
