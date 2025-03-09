/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Administrator
 */
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB (buffer before writing to disk)
        maxFileSize = 50 * 1024 * 1024, // 50MB per file
        maxRequestSize = 250 * 1024 * 1024 // 250MB total request
)

public class UploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UploadServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.DB.getConnection();
            if (conn == null) {
                response.getWriter().write("Database connection failed.");
                return;
            }
            conn.setAutoCommit(false); // Begin transaction

            String sql = "INSERT INTO pdf_files (file_name, file_data) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);

            Collection<Part> fileParts = request.getParts();
            boolean fileUploaded = false;

            for (Part part : fileParts) {
                if (part.getName().equals("pdfFiles") && part.getSize() > 0) {
                    String fileName = part.getSubmittedFileName();
                    InputStream fileStream = part.getInputStream();

                    System.out.println("Processing File: " + fileName);
                    System.out.println("File Size: " + part.getSize());

                    if (fileStream.available() == 0) {
                        response.getWriter().write("File stream is empty for: " + fileName);
                        return;
                    }

                    stmt.setString(1, fileName);
                    stmt.setBlob(2, fileStream);
                    stmt.addBatch();
                    fileUploaded = true;
                }
            }

            if (fileUploaded) {
                stmt.executeBatch();  // Execute batch insert
                conn.commit();        // Commit transaction
                response.getWriter().write("File(s) uploaded successfully!");
            } else {
                response.getWriter().write("No valid files uploaded.");
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();  // Rollback on error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.getWriter().write("Database Error: " + e.getMessage());
        } catch (Exception e) {
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
