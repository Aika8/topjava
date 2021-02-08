<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<html>
<head>
    <title>Home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <h1>Meals</h1>
        <div class="col-sm-12">
            <a class="btn btn-primary mb-3" href="meals?action=add">Add Meal</a>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Description</th>
                    <th scope="col">Calories</th>
                    <th scope="col" colspan="2">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<MealTo> meals = (ArrayList<MealTo>) request.getAttribute("meals");
                    if (meals != null) {
                        for (MealTo meal : meals) {
                %>
                <tr style="color: <%= meal.isExcess()? "red" : "green" %>">
                    <th><%=meal.getDateTime().format(formatter)%>
                    </th>
                    <td><%= meal.getDescription() %>
                    </td>
                    <td><%= meal.getCalories() %>
                    </td>
                    <td><a href="meals?action=edit&mealId=<%=meal.getId()%>">Update</a></td>
                    <td><a href="meals?action=delete&mealId=<%=meal.getId()%>">Delete</a></td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>


        </div>
    </div>

</div>
</body>
</html>
