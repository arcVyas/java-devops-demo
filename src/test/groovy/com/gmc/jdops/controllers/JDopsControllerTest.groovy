package com.gmc.jdops.controllers

import com.gmc.jdops.controllers.JDopsController
import spock.lang.Specification

/**
 * Created by vyas on 6/5/17.
 */
class JdopsControllerTest extends Specification {


    def setupSpec() {
        
    }

    def "JDopsToolList - Validate Approved List Count"() {
        def jDops = new JDopsController()
        List toolList = jDops.getJDopsTools()
        expect:
            toolList.size() > 0
    }

    def "JDopsToolList - Validate Approved List Status"() {
        setup:
        def jDops = new JDopsController()

        when:
        List toolList = jDops.getJDopsTools()

        then:
            toolList.each { tool ->
                if(!tool.name.contains("Jenkins")) {
                    assert tool.isApproved : "${tool.name} approval status is ${tool.isApproved}. It should have been approved"
                }
            }
    }

    def "JDopsToolList - Validate tool creation"() {
        setup:
        def jDops = new JDopsController()

        when:
        String status = jDops.createJDopsTool(null)

        then:
            status == "200"
    }

    def "JDopsToolList - Validate tool deletion"() {
        setup:
        def jDops = new JDopsController()

        when:
        String status = jDops.deleteJDopsTool(null)

        then:
            status == "200"
    }

    def "Test test method"(){
        setup:
        def jDops = new JDopsController()

        when:
        String tool=jDops.testMethod()

        then:
            tool == "test"
    }
    

    def "App version should be >= 1.0"() {
        expect:
            Float.parseFloat(new JDopsController().getVersion()) >= 1.0
    }

   
}
