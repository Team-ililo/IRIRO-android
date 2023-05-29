package com.example.ililo.Park.model

import com.example.ililo.BaseResponse

data class NearVehicleRes(
    val data: ArrayList<NearVehicleList>
): BaseResponse()
