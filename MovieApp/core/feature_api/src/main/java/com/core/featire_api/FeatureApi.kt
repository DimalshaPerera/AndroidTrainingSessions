package com.core.featire_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController


interface FeatureApi {
    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )
}