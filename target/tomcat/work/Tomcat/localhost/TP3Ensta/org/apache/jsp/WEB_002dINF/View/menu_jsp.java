/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-04-04 21:05:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("  <ul id=\"slide-out\" class=\"side-nav fixed z-depth-4\">\n");
      out.write("    <li><a class=\"active\" href=\"dashboard\"><i class=\"material-icons pink-item\">dashboard</i>Accueil</a></li>\n");
      out.write("\n");
      out.write("    <li><a class=\"subheader\">Gestion des membres</a></li>\n");
      out.write("    <li><a href=\"membre_list\"><ion-icon class=\"menu-item pink-item\" name=\"people\"></ion-icon>Liste des membres</a></li>\n");
      out.write("    <li><a href=\"membre_add\"><ion-icon class=\"menu-item pink-item\" name=\"person-add\"></ion-icon>Ajouter un membre</a></li>\n");
      out.write("\n");
      out.write("    <li><a class=\"subheader\">Gestion des livres</a></li>\n");
      out.write("    <li><a href=\"livre_list\"><ion-icon class=\"menu-item pink-item\" name=\"book\"></ion-icon>Liste des livres</a></li>\n");
      out.write("    <li><a href=\"livre_add\"><i class=\"material-icons pink-item\">note_add</i>Ajouter un livre</a></li>\n");
      out.write("\n");
      out.write("    <li><a class=\"subheader\">Gestion des emprunts</a></li>\n");
      out.write("    <li><a href=\"emprunt_add\"><ion-icon class=\"menu-item pink-item\" name=\"log-out\"></ion-icon>Emprunter un livre</a></li>\n");
      out.write("    <li><a href=\"emprunt_return\"><ion-icon class=\"menu-item pink-item\" name=\"log-in\"></ion-icon>Retourner un livre</a></li>\n");
      out.write("    <li><a href=\"emprunt_list\"><i class=\"material-icons pink-item\">swap_horiz</i>Emprunts en cours</a></li>\n");
      out.write("    <li><a href=\"emprunt_list?show=all\"><i class=\"material-icons pink-item\">swap_horizontal_circle</i>Tous les emprunts</a></li>\n");
      out.write("  </ul>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
