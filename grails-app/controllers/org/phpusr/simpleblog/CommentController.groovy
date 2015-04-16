package org.phpusr.simpleblog

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class CommentController {

    static allowedMethods = [deleteAjax: "POST"]

    def userService

    private static OkStatus = [status: 'OK']

    @Secured(['permitAll'])
    def listAjax(Long articleId) {
        def comments = Comment.findAll {
            article.id == articleId
        }

        render(contentType: "text/json") {
            array {
                for (comment in comments) {
                    c = {
                        id = comment.id
                        body = comment.body
                        userId = comment.userId
                        userName = comment.user.username
                    }
                }
            }
        }
    }

    def addAjax(Comment comment) {
        comment.user = userService.user
        comment.save(flush: true)

        render OkStatus as JSON
    }

    @Secured(['ROLE_ADMIN'])
    def deleteAjax(Comment comment) {
        comment.delete(flush: true)

        render OkStatus as JSON
    }

}
