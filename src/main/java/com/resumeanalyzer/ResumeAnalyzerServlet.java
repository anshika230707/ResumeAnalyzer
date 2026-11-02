package com.resumeanalyzer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@WebServlet("/analyze")
@MultipartConfig
public class ResumeAnalyzerServlet extends HttpServlet {

    private static final List<String> SKILLS = Arrays.asList(
            "java",
            "python",
            "javascript",
            "html",
            "css",
            "react",
            "sql",
            "mysql",
            "postgresql",
            "mongodb",
            "spring",
            "spring boot",
            "git",
            "github",
            "machine learning",
            "data science"
    );

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Part resumePart = request.getPart("resume");

        String jobDescription =
                request.getParameter("jobDescription");

        if (resumePart == null ||
                resumePart.getSize() == 0) {

            response.getWriter().println(
                    "Please upload a resume."
            );

            return;
        }

        String resumeText =
                extractTextFromPDF(
                        resumePart.getInputStream()
                );

        resumeText = resumeText.toLowerCase();

        jobDescription =
                jobDescription.toLowerCase();

        List<String> matchedSkills =
                new ArrayList<>();

        List<String> missingSkills =
                new ArrayList<>();

        int requiredSkills = 0;

        for (String skill : SKILLS) {

            if (jobDescription.contains(skill)) {

                requiredSkills++;

                if (resumeText.contains(skill)) {

                    matchedSkills.add(skill);

                } else {

                    missingSkills.add(skill);
                }
            }
        }

        int score = 0;

        if (requiredSkills > 0) {

            score = (matchedSkills.size() * 100)
                    / requiredSkills;
        }

        request.setAttribute(
                "score",
                score
        );

        request.setAttribute(
                "matchedSkills",
                matchedSkills
        );

        request.setAttribute(
                "missingSkills",
                missingSkills
        );

        request.getRequestDispatcher(
                "result.jsp"
        ).forward(request, response);
    }


    private String extractTextFromPDF(
            InputStream inputStream)
            throws IOException {

        byte[] pdfBytes =
                inputStream.readAllBytes();

        try (PDDocument document =
                     Loader.loadPDF(pdfBytes)) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}