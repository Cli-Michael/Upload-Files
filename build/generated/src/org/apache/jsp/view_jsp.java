package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <title>Uploaded PDF Files</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("    <script>\n");
      out.write("        function showLoading(button) {\n");
      out.write("            button.disabled = true;\n");
      out.write("            button.innerHTML = '<span class=\"spinner-border spinner-border-sm\"></span> Deleting...';\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body class=\"bg-light\">\n");
      out.write("\n");
      out.write("    <div class=\"container mt-5\">\n");
      out.write("        <h2 class=\"mb-4 text-center\">📄 Uploaded PDF Files</h2>\n");
      out.write("\n");
      out.write("        <!-- Success/Error Messages -->\n");
      out.write("        ");
 if (request.getParameter("success") != null) { 
      out.write("\n");
      out.write("            <div class=\"alert alert-success\">PDF deleted successfully!</div>\n");
      out.write("        ");
 } else if (request.getParameter("error") != null) { 
      out.write("\n");
      out.write("            <div class=\"alert alert-danger\">Error deleting PDF. Try again.</div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"card shadow-sm p-4 bg-white\">\n");
      out.write("            <table class=\"table table-bordered table-hover\">\n");
      out.write("                <thead class=\"table-dark\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th>ID</th>\n");
      out.write("                        <th>File Name</th>\n");
      out.write("                        <th>View</th>\n");
      out.write("                        <th>Delete</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;
                        try {
                            conn = Database.DB.getConnection();
                            String sql = "SELECT id, filename FROM pdf_files";
                            stmt = conn.prepareStatement(sql);
                            rs = stmt.executeQuery();

                            boolean hasFiles = false;
                            while (rs.next()) {
                                hasFiles = true;
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( rs.getInt("id") );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( rs.getString("filename") );
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"ViewPDFServlet?id=");
      out.print( rs.getInt("id") );
      out.write("\" target=\"_blank\" class=\"btn btn-sm btn-primary\">\n");
      out.write("                                📂 View PDF\n");
      out.write("                            </a>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <form action=\"DeletePDFServlet\" method=\"post\" onsubmit=\"showLoading(this.querySelector('button'))\">\n");
      out.write("                                <input type=\"hidden\" name=\"id\" value=\"");
      out.print( rs.getInt("id") );
      out.write("\">\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-sm btn-danger\">\n");
      out.write("                                    ❌ Delete\n");
      out.write("                                </button>\n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                            }
                            if (!hasFiles) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"4\" class=\"text-center text-muted\">No PDFs uploaded yet.</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"4\" class=\"text-center text-danger\">Error loading PDFs.</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        } finally {
                            if (rs != null) {
                                try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                            }
                            if (stmt != null) {
                                try { stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                            }
                            if (conn != null) {
                                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                            }
                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Navigation Buttons -->\n");
      out.write("        <div class=\"text-center mt-4\">\n");
      out.write("            <a href=\"upload.jsp\" class=\"btn btn-success\">⬆ Upload New PDF</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
