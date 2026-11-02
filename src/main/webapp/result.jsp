<%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Resume Analysis Result</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<div class="container">

    <div class="card">

        <h1>Resume Analysis Result</h1>

        <h2>Resume Score</h2>

        <h3>
            <%= request.getAttribute("score") %>%
        </h3>

        <h2>Matched Skills</h2>

        <ul>

        <%
            List<String> matchedSkills =
                (List<String>) request.getAttribute(
                    "matchedSkills"
                );

            for (String skill : matchedSkills) {
        %>

            <li>
                <%= skill %>
            </li>

        <%
            }
        %>

        </ul>


        <h2>Missing Skills</h2>

        <ul>

        <%
            List<String> missingSkills =
                (List<String>) request.getAttribute(
                    "missingSkills"
                );

            for (String skill : missingSkills) {
        %>

            <li>
                <%= skill %>
            </li>

        <%
            }
        %>

        </ul>

        <br>

        <a href="index.html">
            Analyze Another Resume
        </a>

    </div>

</div>

</body>

</html>