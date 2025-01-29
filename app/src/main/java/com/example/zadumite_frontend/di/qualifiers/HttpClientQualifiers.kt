package com.example.zadumite_frontend.di.qualifiers

import org.koin.core.qualifier.named

val AUTHENTICATED_CLIENT = named("AuthenticatedClient")
val TOKEN_REFRESH_CLIENT = named("TokenRefreshClient")
