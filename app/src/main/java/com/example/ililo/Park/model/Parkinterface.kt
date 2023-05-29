package com.example.ililo.Park.model

interface Parkinterface {
    fun onPostRegisterSuccess(message: String)
    fun onPostRegisterFailure(message: String)

    fun onGetNearVehicleSuccess(response: NearVehicleRes)
    fun onGetNearVehicleFailure(message: String)
}