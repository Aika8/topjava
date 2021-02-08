<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<html>
<head>
    <title>Home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <% Meal meal = (Meal) request.getAttribute("meal"); %>
    <h1 class="mt-4"><%= meal == null ? "Add New Meal" : "Edit Meal"%>
    </h1>
    <div class="row mt-5">
        <div class="col-sm-6">
            <form action="meals" method="post">
                <%
                    if (meal != null) {
                %>
                <input type="hidden" name="mealId" value="<%=meal.getId()%>">
                <%
                    }
                %>
                <div class="form-group">
                    <label>DateTime:</label>
                    <input class="form-control" name="date" type="datetime-local"
                           value="<%=meal !=null ? meal.getDateTime(): ""%>">
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <input class="form-control" name="description" type="text"
                           value="<%=meal !=null ? meal.getDescription(): ""%>">
                </div>
                <div class="form-group">
                    <label>Calories:</label>
                    <input class="form-control" name="calories" type="number"
                           value="<%=meal !=null ? meal.getCalories(): ""%>">
                </div>
                <div class="form-group">
                    <button class="btn btn-success">Save</button>
                    <a class="btn btn-primary" href="meals?action=listMeals">Cancel</a>
                </div>
            </form>


        </div>
    </div>

</div>
</body>
</html>
