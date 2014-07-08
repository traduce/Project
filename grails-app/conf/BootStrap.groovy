import com.traduce.setting.Role
import com.traduce.setting.User
import com.traduce.setting.UserRole

class BootStrap {

    transient springSecurityService
    def init = { servletContext ->
        println("BOOTSTRAP CONFIGURED:: "+User.count())
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        //def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
        def testUser = new User(username: 'test', password: '1234')
        testUser.save(flush: true)
        UserRole.create testUser, adminRole, true
//        assert User.count() == 1
//        assert Role.count() == 2
//        assert UserRole.count() == 1



        // def password = 'test'
        //def user = new User(username : 'test', password:password,enabled:true,
        //accountExpired : false , accountLocked : false ,passwordExpired : false).save(flush: true, insert: true)
       //def role = new Role(authority : 'ROLE_TEST').save(flush: true, failOnError: true)
        //println("USER IS: "+user+" ROLE IS: "+role)
        //create the first user role map
        //  UserRole.create user , role , true
        println("BOOTSTAP!!!")
        this.initRole()
    }
    def destroy = {
    }
    def private user
    def initRole(){
        user=Role.findByAuthority("ROLE_USER")?:new Role(authority: "ROLE_USER").save(failOnError: true)

    }
}
