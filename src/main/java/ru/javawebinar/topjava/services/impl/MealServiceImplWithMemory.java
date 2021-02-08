package ru.javawebinar.topjava.services.impl;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.services.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class MealServiceImplWithMemory implements MealService {

    private static CopyOnWriteArrayList<Meal> meals = new CopyOnWriteArrayList<>();
    private static final int caloriesPerDay = 2000;
    private static AtomicLong id = new AtomicLong(8L);


    static {
        meals.add(new Meal(1L, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(2L, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(3L, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(4L, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(5L, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(6L, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(7L, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }


    @Override
    public List<MealTo> getAllMeals() {
        return MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }


    @Override
    public void addMeal(Meal meal) {
        meal.setId(id.getAndIncrement());
        meals.add(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        for (Meal m : meals) {
            if (m.getId() == meal.getId()) {
                meals.set(meals.indexOf(m), meal);
            }
        }
    }

    @Override
    public void deleteMeal(Long id) {
        meals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public Meal getMeal(Long id) {
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                return meal;
            }
        }

        return null;
    }
}
