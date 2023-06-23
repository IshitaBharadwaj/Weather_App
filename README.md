# Weather App | Android

The purpose of this document is to provide a clear and comprehensive outline of the application that will be developed by Mobile Engineer interns. The application will be a user-friendly weather application consisting of two screens, which will incorporate all the knowledge and skills acquired thus far, including UI development, state management, navigation, user interaction etc with the best practices. 


## Project Scope:
The scope of the project includes the following key components:

* User interface design and development
* Static weather data
* Weather data parsing and processing
* Navigation 
* Updating UI on user action
* State management


### Main Screen

![weatherApp1](https://github.com/IshitaBharadwaj/Weather_App/assets/68071562/15134060-855b-4fe1-a258-24ec5e0233d3)


☁️  Multiple cards are shown as a LazyColumn where each card shows temperature (min, max), current temperature, location and image matching weather condition. <br>
☀️ User is able to search in the data set filtering the results. <br>
☔ On clicking 3 dots, User can refresh the list such that all cards (including the deleted enteries) are revived back as original dataset. <br>
❄️ On long press on any card, the user will be able to delete the item. <br>
🌀 On clicking any card the detail screen will open on top of main screen. <br>


### Detail Screen

![weatherApp2](https://github.com/IshitaBharadwaj/Weather_App/assets/68071562/6fcd86d1-699e-4c4f-8215-c82e8742fca7)

☁️ Detail screen opens when user clicks on a card on main screen.<br>
☀️ Data is passed from main screen to detail screen.<br>
☔  User is able to navigate back to main screen from details screen and the main screen state should be preserved.
<br>
