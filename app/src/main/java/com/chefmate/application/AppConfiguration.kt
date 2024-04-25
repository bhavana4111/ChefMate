package com.chefmate.application

import android.app.Application
import com.chefmate.R
import com.chefmate.ui.model.ProductModel

class AppConfiguration : Application() {
    companion object{
        val filterList =
            listOf(
                "Good source of phosphorus",
                "Good source of vitamin B12",
                "Good source of calcium",
                "Good source of protein",
                "Good source of zinc"
            )
        val list = arrayListOf<ProductModel>().apply {
            add(ProductModel(id = "", image = R.drawable.ic_gluten, name = "Find Me Gluten", rating = "4", price = "120.00", detail = "This simple app makes finding gluten-free foods a breeze. The barcode scanner provides an analysis on four levels for more information and greater accuracy. The app’s database has over 500,000 products and growing, so you can check for gluten in all of your favorite foods and beverages."))
            add(ProductModel(id = "", image = R.drawable.ic_shopwell, name = "ShopWell – Better Food Choices", rating = "4", price = "130.00", detail = "ShopWell helps you scan food during your grocery trips to understand how they affect you within the context of your own personalized Food Profile, which calculates the nutritional value of your food based on your desired diet, health issues, or food allergies. The app can use your location to tell you exactly which products are available in your local grocery store to better plan when and where to buy healthy food."))
            add(ProductModel(id = "", image = R.drawable.ic_mealime, name = "Mealime Meal Plans & Recipes", rating = "3", price = "140.00", detail = "Make a personalized meal plan for your week and choose from thousands of recipes that you can make in 30 minutes or less. This app can be a huge help if you’re busy and want to eat healthy but can’t seem to find the time to fit in a quick, healthy meal. It can help you optimize your grocery lists to consistently buy nutritious food and even follow a meal plan to makes sure as little food as possible goes to waste."))
            add(ProductModel(id = "", image = R.drawable.ic_yum, name = "Yummly Recipes + Shopping List", rating = "3", price = "150.00", detail = "Choose from literally millions of recipes to cook anything you want, especially if you need something healthy and quick. Keep running lists of what’s in your fridge right now and can select recipes that’ll help you use what you have and let nothing go to waste. You can also schedule recipes on your calendar so that you can plan your meal preps ahead of time and reduce how much you have to think about your weekly meals."))
            add(ProductModel(id = "", image = R.drawable.ic_fooducate, name = "Fooducate – Eat Better Coach", rating = "2", price = "160.00", detail = "Keep track of what you’re eating, how often you exercise, and how healthy your calories really are rather than just how many you’re eating. Scan food barcodes to see their grade on an A to F scale and detailed information about their nutritional content and hidden dangers like empty carbs and added sugars. Sync the app with your Apple Health app so that you can see your diet along with other crucial health information like your sleep quality."))
            add(ProductModel(id = "", image = R.drawable.ic_healthy, name = "Healthy Recipes & Calculator", rating = "2", price = "180.00", detail = "Search through a huge database of recipes for any type of meal or occasion along with detailed nutritional information for each recipe powered by the app’s companion site SparkRecipes.com. Keep a list of your favorite recipes, share your recipes with other app users, and sync your diet information with the SparkPeople fitness and exercise app."))
            add(ProductModel(id = "", image = R.drawable.ic_fit, name = "Fit Men Cook", rating = "1", price = "170.00", detail = "Fit Men Cook founder Kevin Curry didn’t like the way he looked on a friend’s social media post, so he aimed to get fit: starting with cooking more thoughtfully and healthy. His Fit Men Cook app offers all the tools that worked to make his dieting journey easier. These include a shopping cart feature that syncs with Apple Watch, meal-prep guides, step-by-step video recipe instructions, and a big database of recipes that are easy to search using ingredients that you already have."))
            add(ProductModel(id = "", image = R.drawable.ic_healthy_recipe, name = "Healthy Food Recipes", rating = "1", price = "180.00", detail = "Whether you want to start losing weight or just be more thoughtful about the ingredients you’re putting in your recipes to reduce gluten, this app contains a variety of recipes for any type of meal, celebration, fitness plan, and diet types (including gluten-free). There are also many Crock-Pot and slow cooker recipes so that you can set up a healthy meal that’ll be ready for you by the end of the day."))
            add(ProductModel(id = "", image = R.drawable.ic_food, name = "Food Intolerances", rating = "4", price = "190.00", detail = "Food intolerance can feel like science — because learning how your body responds to thousands of different vitamins, minerals, and nutrients is a big, complex task. Get all the info you need for gluten-free eating with the Food Intolerances app, along with numerous other food allergy information, including carbohydrate malabsorption and histamine intolerances. Build your gluten-free diet around your new knowledge of food intolerances as they specifically apply to you."))
        }
        var ratingList = arrayListOf<String>().apply {
            add("1")
            add("2")
            add("3")
            add("4")
            add("5")
        }
    }
}