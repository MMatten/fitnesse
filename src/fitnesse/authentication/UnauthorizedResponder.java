// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.
package fitnesse.authentication;

import fitnesse.FitNesseContext;
import fitnesse.Responder;
import fitnesse.http.Request;
import fitnesse.http.Response;
import fitnesse.http.SimpleResponse;
import fitnesse.html.template.HtmlPage;

public class UnauthorizedResponder implements Responder {
  
  @Override
  public Response makeResponse(FitNesseContext context, Request request) {
    SimpleResponse response = new SimpleResponse(401);
    response.addHeader("WWW-Authenticate", "Basic realm=\"FitNesse\"");

    HtmlPage page = context.pageFactory.newPage();
    page.addTitles("401 Unauthorized");
    page.put("resource", request.getResource());
    page.setMainTemplate("unauthorized.vm");
    response.setContent(page.html());

    return response;
  }

}
