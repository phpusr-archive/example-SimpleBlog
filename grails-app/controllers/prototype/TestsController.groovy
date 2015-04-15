package prototype

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class TestsController {

    def index() {
        render "It is work!"
    }

}
