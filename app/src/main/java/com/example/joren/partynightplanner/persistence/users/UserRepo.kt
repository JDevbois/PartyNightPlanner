package com.example.joren.partynightplanner.persistence.users

import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.util.DummyData

class UserRepo {
    companion object {
        fun findUserById(id: String): User {
            return DummyData.users.first { u -> u.id == id }
        }

    }
}
