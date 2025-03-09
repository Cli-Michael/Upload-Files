package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class upload_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Upload Multiple PDFs</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">\n");
      out.write("    \n");
      out.write("    <style>\n");
      out.write("        #dropArea {\n");
      out.write("            border: 2px dashed #007bff;\n");
      out.write("            padding: 30px;\n");
      out.write("            text-align: center;\n");
      out.write("            background-color: #f8f9fa;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        #dropArea.dragover {\n");
      out.write("            background-color: #e9ecef;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function uploadFiles() {\n");
      out.write("            var fileInput = document.getElementById(\"pdfFiles\");\n");
      out.write("            var fileList = fileInput.files;\n");
      out.write("            var alertBox = document.getElementById(\"alertBox\");\n");
      out.write("            var progressBar = document.getElementById(\"progressBar\");\n");
      out.write("            var progressText = document.getElementById(\"progressText\");\n");
      out.write("\n");
      out.write("            if (fileList.length === 0) {\n");
      out.write("                alertBox.innerHTML = '<div class=\"alert alert-warning\">Please select files to upload.</div>';\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            var formData = new FormData();\n");
      out.write("            for (let i = 0; i < fileList.length; i++) {\n");
      out.write("                let file = fileList[i];\n");
      out.write("\n");
      out.write("                // Validate file type (only PDFs)\n");
      out.write("                if (file.type !== \"application/pdf\") {\n");
      out.write("                    alertBox.innerHTML = '<div class=\"alert alert-danger\">\"' + file.name + '\" is not a PDF file.</div>';\n");
      out.write("                    return;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // Validate file size (Max 50MB per file)\n");
      out.write("                if (file.size > 50 * 1024 * 1024) {\n");
      out.write("                    alertBox.innerHTML = '<div class=\"alert alert-danger\">\"' + file.name + '\" exceeds 50MB. Please choose a smaller file.</div>';\n");
      out.write("                    return;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                formData.append(\"pdfFiles\", file);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            var xhr = new XMLHttpRequest();\n");
      out.write("            xhr.open(\"POST\", \"UploadServlet\", true);\n");
      out.write("\n");
      out.write("            // Show progress bar\n");
      out.write("            progressBar.style.display = \"block\";\n");
      out.write("            progressBar.value = 0;\n");
      out.write("            progressText.innerText = \"Uploading...\";\n");
      out.write("\n");
      out.write("            // Track upload progress (combined progress for all files)\n");
      out.write("            xhr.upload.onprogress = function(event) {\n");
      out.write("                if (event.lengthComputable) {\n");
      out.write("                    var percentComplete = (event.loaded / event.total) * 100;\n");
      out.write("                    progressBar.value = percentComplete;\n");
      out.write("                    progressText.innerText = Math.round(percentComplete) + \"% uploaded\";\n");
      out.write("                }\n");
      out.write("            };\n");
      out.write("\n");
      out.write("            xhr.onload = function() {\n");
      out.write("                if (xhr.status === 200) {\n");
      out.write("                    alertBox.innerHTML = '<div class=\"alert alert-success\">Files uploaded successfully!</div>';\n");
      out.write("                    progressBar.value = 100;\n");
      out.write("                    progressText.innerText = \"Upload Complete!\";\n");
      out.write("                    fileInput.value = \"\"; // Clear input field after success\n");
      out.write("                } else {\n");
      out.write("                    alertBox.innerHTML = '<div class=\"alert alert-danger\">Upload failed. Server error.</div>';\n");
      out.write("                }\n");
      out.write("            };\n");
      out.write("\n");
      out.write("            xhr.send(formData);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        // Drag & Drop Feature\n");
      out.write("        function handleDrop(event) {\n");
      out.write("            event.preventDefault();\n");
      out.write("            document.getElementById(\"dropArea\").classList.remove(\"dragover\");\n");
      out.write("            document.getElementById(\"pdfFiles\").files = event.dataTransfer.files;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function handleDragOver(event) {\n");
      out.write("            event.preventDefault();\n");
      out.write("            document.getElementById(\"dropArea\").classList.add(\"dragover\");\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function handleDragLeave(event) {\n");
      out.write("            document.getElementById(\"dropArea\").classList.remove(\"dragover\");\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body class=\"bg-light\">\n");
      out.write("\n");
      out.write("    <div class=\"container mt-5\">\n");
      out.write("        <h2 class=\"mb-4 text-center\">📤 Upload Multiple PDFs - PIS1</h2>\n");
      out.write("\n");
      out.write("        <div id=\"alertBox\"></div> <!-- Alerts for success/errors -->\n");
      out.write("\n");
      out.write("        <div class=\"card shadow-sm p-4 bg-white\">\n");
      out.write("            <form id=\"uploadForm\" enctype=\"multipart/form-data\" onsubmit=\"event.preventDefault(); uploadFiles();\">\n");
      out.write("                \n");
      out.write("                <!-- Drag & Drop Area -->\n");
      out.write("                <div id=\"dropArea\" ondrop=\"handleDrop(event)\" ondragover=\"handleDragOver(event)\" ondragleave=\"handleDragLeave(event)\">\n");
      out.write("                    <p>Drag & Drop PDFs here or Click to Select</p>\n");
      out.write("                    <input type=\"file\" id=\"pdfFiles\" name=\"pdfFiles\" accept=\"application/pdf\" class=\"form-control\" multiple required>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <button type=\"submit\" class=\"btn btn-primary w-100 mt-3\">📤 Upload Files</button>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            <!-- Progress Bar -->\n");
      out.write("            <progress id=\"progressBar\" value=\"0\" max=\"100\" class=\"w-100 mt-3\" style=\"display: none;\"></progress>\n");
      out.write("            <p id=\"progressText\" class=\"text-center\"></p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Navigation Buttons -->\n");
      out.write("        <div class=\"text-center mt-4\">\n");
      out.write("            <a href=\"view.jsp\" class=\"btn btn-success\">📂 View Uploaded PDFs</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    \n");
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
