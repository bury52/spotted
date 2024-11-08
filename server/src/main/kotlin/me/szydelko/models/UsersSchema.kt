package me.szydelko.models

import me.szydelko.utils.notNull
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class UserService(database: Database) {
    object Users : LongIdTable() {
        val email = varchar("email", 255).uniqueIndex()
        val password = varchar("password", 255)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun create(email: String, password: String): Long {
        return dbQuery {
            Users.insertAndGetId {
                it[Users.email] = email
                it[Users.password] = BCrypt.hashpw(password, BCrypt.gensalt())
            }
        }.value
    }

    suspend fun read(id: Long): ResultRow? {
        return dbQuery {
            Users.selectAll().where { Users.id eq id }.singleOrNull()
        }
    }

    suspend fun read(email: String): ResultRow? {
        return dbQuery {
            Users.selectAll().where { Users.email eq email }.singleOrNull()
        }
    }

    suspend fun update(id: Long, email: String?, password: String?) {
        dbQuery {
            Users.update({ Users.id eq id }) { dbUser ->
                email notNull { dbUser[Users.email] = it }
                password notNull {
                    dbUser[Users.password] = BCrypt.hashpw(it, BCrypt.gensalt())
                }
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Users.deleteWhere { Users.id eq id }
        }
    }

    /**
     * @return first = is verified, second = id
     */
    suspend fun verifyPassword(email: String, password: String): Pair<Boolean, Long> {
        read(email) notNull {
            return Pair(BCrypt.checkpw(password, it[Users.password]), it[Users.id].value)
        }
        return Pair(false, 0)
    }


}

