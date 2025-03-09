<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Uploaded PDF Files</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        function showLoading(button) {
            button.disabled = true;
            button.innerHTML = '<span class="spinner-border spinner-border-sm"></span> Deleting...';
        }
    </script>
</head>
<body class="bg-light">

    <div class="container mt-5">
        <h2 class="mb-4 text-center">📄 Uploaded PDF Files</h2>

        <!-- Success/Error Messages -->
        <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success">PDF deleted successfully!</div>
        <% } else if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger">Error deleting PDF. Try again.</div>
        <% } %>

        <div class="card shadow-sm p-4 bg-white">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>File Name</th>
                        <th>View</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;
                        try {
                            conn = Database.DB.getConnection();
                            String sql = "SELECT id, file_name FROM pdf_files";
                            stmt = conn.prepareStatement(sql);
                            rs = stmt.executeQuery();

                            boolean hasFiles = false;
                            while (rs.next()) {
                                hasFiles = true;
                    %>
                    <tr>
                        <td><%= rs.getInt("id") %></td>
                        <td><%= rs.getString("file_name") %></td>
                        <td>
                            <a href="ViewPDFServlet?id=<%= rs.getInt("id") %>" target="_blank" class="btn btn-sm btn-primary">
                                📂 View PDF
                            </a>
                        </td>
                        <td>
                            <form action="DeletePDFServlet" method="post" onsubmit="showLoading(this.querySelector('button'))">
                                <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                                <button type="submit" class="btn btn-sm btn-danger">
                                    ❌ Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                            if (!hasFiles) {
                    %>
                    <tr>
                        <td colspan="4" class="text-center text-muted">No PDFs uploaded yet.</td>
                    </tr>
                    <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                    %>
                    <tr>
                        <td colspan="4" class="text-center text-danger">Error loading PDFs.</td>
                    </tr>
                    <%
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
                    %>
                </tbody>
            </table>
        </div>

        <!-- Navigation Buttons -->
        <div class="text-center mt-4">
            <a href="upload.jsp" class="btn btn-success">⬆ Upload New PDF</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
