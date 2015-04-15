import org.phpusr.simpleblog.Role
import org.phpusr.simpleblog.User
import org.phpusr.simpleblog.UserRole

class BootStrap {

    def init = { servletContext ->

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def writerRole = Role.findByAuthority('ROLE_WRITER') ?: new Role(authority: 'ROLE_WRITER').save(failOnError: true)

        def adminUser = User.findByUsername('admin') ?: new User(username: 'admin', password: '123', enabled: true).save(failOnError: true)
        def userUser = User.findByUsername('user') ?: new User(username: 'user', password: '123', enabled: true).save(failOnError: true)
        def writerUser = User.findByUsername('writer') ?: new User(username: 'writer', password: '123', enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole))  UserRole.create(adminUser, adminRole)
        if (!adminUser.authorities.contains(writerRole)) UserRole.create(adminUser, writerRole)
        if (!userUser.authorities.contains(userRole)) UserRole.create(userUser, userRole)
        if (!writerUser.authorities.contains(writerRole)) UserRole.create(writerUser, writerRole)

    }
    def destroy = {
    }
}
