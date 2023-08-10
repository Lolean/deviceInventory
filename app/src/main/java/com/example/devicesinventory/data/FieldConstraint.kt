package com.example.devicesinventory.data

enum class FieldConstraint {;

    enum class type{
        PHONE,
        TABLET;
    }

    enum class state{
        AVAILABLE,
        TAKEN;
    }

    enum class credential{
        BASIC,
        MODERATOR,
        ADMIN;
    }



}