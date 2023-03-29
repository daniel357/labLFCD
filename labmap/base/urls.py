from django.urls import path
from base import views

urlpatterns = [
    path('', views.HomePageView, name="home-page"),
    path('airport/', views.airportHomePageView, name="airport-home-page"),
    path('list-airport/', views.airportList, name="airport-list"),
    path('create-airport/', views.createAirport, name="airport-create"),
    path('update-airport/<str:pk>/', views.updateAirport, name="airport-update"),
    path('delete-airport/<str:pk>/', views.deleteAirport, name="airport-delete"),
    path('read-airport/<str:pk>/', views.readAirport, name="airport-read"),
    path('filter-airport/<int:pk>/', views.filter_airport, name="airport-filter"),

    path('aircraft/', views.aircraftHomePageView, name="aircraft-home-page"),
    path('list-aircraft/', views.aircraftList, name="aircraft-list"),
    path('create-aircraft/', views.createAircraft, name="aircraft-create"),
    path('update-aircraft/<str:pk>/', views.updateAircraft, name="aircraft-update"),
    path('delete-aircraft/<str:pk>/', views.deleteAircraft, name="aircraft-delete"),
    path('read-aircraft/<str:pk>/', views.readAircraft, name="aircraft-read"),

    path('airline/', views.airlineHomePageView, name="airline-home-page"),
    path('list-airline/', views.airlineList, name="airline-list"),
    path('create-airline/', views.createAirline, name="aircraft-create"),
    path('update-airline/<str:pk>/', views.updateAirline, name="airline-update"),
    path('delete-airline/<str:pk>/', views.deleteAirline, name="airline-delete"),
    path('read-airline/<str:pk>/', views.readAirline, name="airline-read"),

    path('passenger/', views.passengerHomePageView, name="passenger-home-page"),
    path('list-passenger/', views.passengerList, name="passenger-list"),
    path('create-passenger/', views.createPassenger, name="passenger-create"),
    path('update-passenger/<str:pk>/', views.updatePassenger, name="passenger-update"),
    path('delete-passenger/<str:pk>/', views.deletePassenger, name="passenger-delete"),
    path('read-passenger/<str:pk>/', views.readPassenger, name="passenger-read"),

    path('flight/', views.flightHomePageView, name="flight-home-page"),
    path('list-flight/', views.flightList, name="flight-list"),
    path('create-flight/', views.createFlight, name="flight-create"),
    path('update-flight/<str:pk>/', views.updateFlight, name="flight-update"),
    path('delete-flight/<str:pk>/', views.deleteFlight, name="flight-delete"),
    path('read-flight/<str:pk>/', views.readFlight, name="flight-read"),

    path('ticket/', views.ticketHomePageView, name="ticket-home-page"),
    path('list-ticket/', views.ticketList, name="ticket-list"),
    path('create-ticket/', views.createTicket, name="ticket-create"),
    path('update-ticket/<str:pk>/', views.updateTicket, name="ticket-update"),
    path('delete-ticket/<str:pk>/', views.deleteTicket, name="ticket-delete"),
    path('read-ticket/<str:pk>/', views.readTicket, name="ticket-read"),

    path('operating-flights/', views.OperatingFlightsHomePageView, name="operating-flights-home-page"),
    path('list-operating-flights/', views.OperatingFlightsList, name="operating-flights-list"),
    path('create-operating-flights/', views.createOperatingFlights, name="operating-flights-create"),
    path('update-operating-flights/<str:pk>/', views.updateOperatingFlights, name="operating-flights-update"),
    path('delete-operating-flights/<str:pk>/', views.deleteOperatingFlights, name="operating-flights-delete"),
    path('read-operating-flights/<str:pk>/', views.readOperatingFlights, name="operating-flights-read"),

    path('airline-stats/', views.airline_revenue_report_view, name="airline-stats"),
    path('flights_passengers_report/', views.flights_passengers_report, name="flights_passengers_report"),

]
