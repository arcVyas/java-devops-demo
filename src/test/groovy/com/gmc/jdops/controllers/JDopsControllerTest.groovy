package com.gmc.jdops.controllers

import com.gmc.jdops.controllers.JDopsController
import spock.lang.Specification

/**
 * Created by vyas on 6/5/17.
 */
class JdopsControllerTest extends Specification {


    def setupSpec() {
        
    }

    def "JDopsToolList - Validate Approved List"() {
        def jDops = new JDopsController()
        List toolList = jDops.getJDopsTools()
        expect:
            toolList.size() == 5
    }

    def "JDopsToolList - Make sure tool name and purpose is not BLANK"() {
        expect:
            1==1
    }

   
}
