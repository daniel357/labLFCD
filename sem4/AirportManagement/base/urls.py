from django.urls import path
from base import viewsAirport, viewsAircraft, viewsAirline

urlpatterns = [
    path('airport/', viewsAirport.airportHomePageView, name="airport-home-page"),
    path('list-airport/', viewsAirport.airportList, name="airport-list"),
    path('create-airport/', viewsAirport.createAirport, name="airport-create"),
    path('update-airport/<str:pk>/', viewsAirport.updateAirport, name="airport-update"),
    path('delete-airport/<str:pk>/', viewsAirport.deleteAirport, name="airport-delete"),
    path('read-airport/<str:pk>/', viewsAirport.readAirport, name="airport-read"),

    path('aircraft/', viewsAircraft.aircraftHomePageView, name="aircraft-home-page"),
    path('list-aircraft/', viewsAircraft.aircraftList, name="aircraft-list"),
    path('create-aircraft/', viewsAircraft.createAircraft, name="aircraft-create"),
    path('update-aircraft/<str:pk>/', viewsAircraft.updateAircraft, name="aircraft-update"),
    path('delete-aircraft/<str:pk>/', viewsAircraft.deleteAircraft, name="aircraft-delete"),
    path('read-aircraft/<str:pk>/', viewsAircraft.readAircraft, name="aircraft-read"),

    path('airline/', viewsAirline.airlineHomePageView, name="airline-home-page"),
    path('list-airline/', viewsAirline.airlineList, name="airline-list"),
    path('create-airline/', viewsAirline.createAirline, name="aircraft-create"),
    path('update-airline/<str:pk>/', viewsAirline.updateAirline, name="airline-update"),
    path('delete-airline/<str:pk>/', viewsAirline.deleteAirline, name="airline-delete"),
    path('read-airline/<str:pk>/', viewsAirline.readAirline, name="airline-read")
]
