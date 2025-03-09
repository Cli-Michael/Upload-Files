<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload Multiple PDFs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    
    <style>
        #dropArea {
            border: 2px dashed #007bff;
            padding: 30px;
            text-align: center;
            background-color: #f8f9fa;
            cursor: pointer;
        }
        #dropArea.dragover {
            background-color: #e9ecef;
        }
    </style>

    <script>
        function uploadFiles() {
            var fileInput = document.getElementById("pdfFiles");
            var fileList = fileInput.files;
            var alertBox = document.getElementById("alertBox");
            var progressBar = document.getElementById("progressBar");
            var progressText = document.getElementById("progressText");

            if (fileList.length === 0) {
                alertBox.innerHTML = '<div class="alert alert-warning">Please select files to upload.</div>';
                return;
            }

            var formData = new FormData();
            for (let i = 0; i < fileList.length; i++) {
                let file = fileList[i];

                // Validate file type (only PDFs)
                if (file.type !== "application/pdf") {
                    alertBox.innerHTML = '<div class="alert alert-danger">"' + file.name + '" is not a PDF file.</div>';
                    return;
                }

                // Validate file size (Max 50MB per file)
                if (file.size > 50 * 1024 * 1024) {
                    alertBox.innerHTML = '<div class="alert alert-danger">"' + file.name + '" exceeds 50MB. Please choose a smaller file.</div>';
                    return;
                }

                formData.append("pdfFiles", file);
            }

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "UploadServlet", true);

            // Show progress bar
            progressBar.style.display = "block";
            progressBar.value = 0;
            progressText.innerText = "Uploading...";

            // Track upload progress (combined progress for all files)
            xhr.upload.onprogress = function(event) {
                if (event.lengthComputable) {
                    var percentComplete = (event.loaded / event.total) * 100;
                    progressBar.value = percentComplete;
                    progressText.innerText = Math.round(percentComplete) + "% uploaded";
                }
            };

            xhr.onload = function() {
                if (xhr.status === 200) {
                    alertBox.innerHTML = '<div class="alert alert-success">Files uploaded successfully!</div>';
                    progressBar.value = 100;
                    progressText.innerText = "Upload Complete!";
                    fileInput.value = ""; // Clear input field after success
                } else {
                    alertBox.innerHTML = '<div class="alert alert-danger">Upload failed. Server error.</div>';
                }
            };

            xhr.send(formData);
        }

        // Drag & Drop Feature
        function handleDrop(event) {
            event.preventDefault();
            document.getElementById("dropArea").classList.remove("dragover");
            document.getElementById("pdfFiles").files = event.dataTransfer.files;
        }

        function handleDragOver(event) {
            event.preventDefault();
            document.getElementById("dropArea").classList.add("dragover");
        }

        function handleDragLeave(event) {
            document.getElementById("dropArea").classList.remove("dragover");
        }
    </script>
</head>
<body class="bg-light">

    <div class="container mt-5">
        <h2 class="mb-4 text-center">📤 Upload Multiple PDFs - PIS1</h2>

        <div id="alertBox"></div> <!-- Alerts for success/errors -->

        <div class="card shadow-sm p-4 bg-white">
            <form id="uploadForm" enctype="multipart/form-data" onsubmit="event.preventDefault(); uploadFiles();">
                
                <!-- Drag & Drop Area -->
                <div id="dropArea" ondrop="handleDrop(event)" ondragover="handleDragOver(event)" ondragleave="handleDragLeave(event)">
                    <p>Drag & Drop PDFs here or Click to Select</p>
                    <input type="file" id="pdfFiles" name="pdfFiles" accept="application/pdf" class="form-control" multiple required>
                </div>

                <button type="submit" class="btn btn-primary w-100 mt-3">📤 Upload Files</button>
            </form>

            <!-- Progress Bar -->
            <progress id="progressBar" value="0" max="100" class="w-100 mt-3" style="display: none;"></progress>
            <p id="progressText" class="text-center"></p>
        </div>

        <!-- Navigation Buttons -->
        <div class="text-center mt-4">
            <a href="view.jsp" class="btn btn-success">📂 View Uploaded PDFs</a>
        </div>
    </div>
    
    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
