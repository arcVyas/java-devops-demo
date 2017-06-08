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
      jDopsToolList.add(new JDopsTool("1","Jira","Collaboration","",true));
      jDopsToolList.add(new JDopsTool("2","Bamboo","CI","",true));
      jDopsToolList.add(new JDopsTool("3","Jenkins","CI","",false));
      jDopsToolList.add(new JDopsTool("4","BitBucket","SCM","",true));
      jDopsToolList.add(new JDopsTool("5","SonarQube","CodeAnalysis","",false));
      return jDopsToolList;
    }

    @ApiOperation(value = "Get API version", response=String.class, responseContainer="String")
    @RequestMapping(value = "/version", produces = "application/json", method=RequestMethod.GET)
    public String getVersion() {
      return "1.0";
    }

}
