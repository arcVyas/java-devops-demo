package com.gmc.jdops.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.gmc.jdops.beans.JDopsTool;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/jdops")
@Api(value="JDops", description="JDOPS APIs")
class JDopsController {

    @ApiOperation(value = "Get JDops Tools", response=JDopsTool.class, responseContainer="List")
    @RequestMapping(value = "/tools", produces = "application/json", method=RequestMethod.GET)
    public List<JDopsTool> getJDopsTools() {
      List jDopsToolList = new ArrayList<JDopsTool>();
      jDopsToolList.add(new JDopsTool("1","Jira","Collaboration","Jira will be primarily used for planning and tracking",true));
      jDopsToolList.add(new JDopsTool("2","Bamboo","CI","",true));
      jDopsToolList.add(new JDopsTool("3","Jenkins","CI","",false));
      jDopsToolList.add(new JDopsTool("4","BitBucket","SCM","",true));
      jDopsToolList.add(new JDopsTool("5","SonarQube","Static Code Analysis","",true));
      jDopsToolList.add(new JDopsTool("6","Gradle","Build Tool","Gradle or Maven can be used",true));
      return jDopsToolList;
    }

    @ApiOperation(value = "Create JDops Tool", response=String.class)
    @RequestMapping(value = "/tools", method=RequestMethod.POST, consumes="application/json")
    public String createJDopsTool(@ApiParam(name="jDopsTool", value="JDops Json", required=true) @RequestBody JDopsTool jDopsTool) {
     return "200";
    }

    @ApiOperation(value = "Delete JDops Tool", response=String.class)
    @RequestMapping(value = "/tools/{id}", method=RequestMethod.DELETE)
    public String deleteJDopsTool(@ApiParam(name="toolId", value="Tool ID", required=true) @PathVariable String toolId) {
     return "200";
    }

    @ApiOperation(value = "Get API version", response=String.class, responseContainer="String")
    @RequestMapping(value = "/version", produces = "application/json", method=RequestMethod.GET)
    public String getVersion() {
      return "1.2";
    }

    public String testMethod(){
     String tool = "test";
     return tool;
    }

    public String testMethodToMakeSonarFail(){
         String tool = "testFailure";
         return tool;
        }
        
    public String testMethodToMakeSonarFail2(){
     String tool = "testFailure2";
     return tool;
    }
    
    public String testMethodToMakeSonarFail3(){
     String tool = "testFailure3";
     return tool;
    }

}