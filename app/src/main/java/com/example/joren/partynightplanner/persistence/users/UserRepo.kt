package com.example.joren.partynightplanner.persistence.users

import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.util.DummyData

//TODO: refactor to use fb api only
class UserRepo {
    companion object {
        val users = DummyData.users

        fun findUserById(id: String): User {
            return DummyData.users.first { u -> u.id == id }
        }

    }
}
