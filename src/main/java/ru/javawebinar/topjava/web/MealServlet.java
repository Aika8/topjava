package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.services.MealService;
import ru.javawebinar.topjava.services.impl.MealServiceImplWithMemory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MealService mealService = new MealServiceImplWithMemory();
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            mealService.deleteMeal(mealId);
            forward = LIST_MEALS;
            request.setAttribute("meals", mealService.getAllMeals());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            Meal meal = mealService.getMeal(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")) {
            forward = LIST_MEALS;
            request.setAttribute("meals", mealService.getAllMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding("UTF-8");
        }

        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        try {
            meal.setCalories(Integer.parseInt(request.getParameter("calories")));
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            meal.setDateTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            mealService.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            mealService.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", mealService.getAllMeals());
        view.forward(request, response);
    }
}
