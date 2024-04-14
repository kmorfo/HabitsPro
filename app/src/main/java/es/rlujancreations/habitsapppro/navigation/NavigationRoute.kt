package es.rlujancreations.habitsapppro.navigation

/**
 * Created by Raúl L.C. on 2/4/24.
 */
sealed class NavigationRoute(val route:String) {
    object  Onboarding:NavigationRoute("onBoarding")
    object  Login:NavigationRoute("login")
    object  Home:NavigationRoute("home")
    object SignUp:NavigationRoute("signUp")
}