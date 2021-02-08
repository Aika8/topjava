package ru.javawebinar.topjava.services;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {

    List<MealTo> getAllMeals();

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(Long id);

    Meal getMeal(Long id);
}
