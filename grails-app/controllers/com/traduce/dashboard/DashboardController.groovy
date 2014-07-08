package com.traduce.dashboard

import com.traduce.setting.User
import grails.plugin.springsecurity.annotation.Secured

//@Secured('permitAll')
//@Secured(['ROLE_ADMIN'])
class DashboardController {
    //def springSecurityService
        def springSecurityService
    def index() {
        println("Dashboard loggedIn>>>>>>>>>> "+springSecurityService.isLoggedIn())

        if (springSecurityService.isLoggedIn()) {
            println("Is loggedIn")
            def role = springSecurityService.getAuthentication().authorities.toString()
            println("Role---- " + role)
            def currentUser = User.findById(springSecurityService.principal.id)
            println("Current user is " + currentUser)
            println("Username user is " + currentUser.username)
            if (currentUser.enabled) {
                if (currentUser.username != null) {
                    session["username"] = currentUser.username
                    session["id"] = currentUser.id
                } else {
                    println "NOT FOUND"
                }
                println("RENDER HERE")
                render(view: 'dashboard')
                //render(view: 'dashboard')

            }
        } else {
            println("Else statement")
            redirect(controller: 'login', action: 'auth')
        }
    }
}
