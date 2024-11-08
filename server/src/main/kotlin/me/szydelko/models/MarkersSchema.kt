package me.szydelko.models

import me.szydelko.models.UserService.Users
import me.szydelko.utils.notNull
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class MarkersService(database: Database) {
    object Markers : LongIdTable() {
        val lat = float("lat")
        val lon = float("lon")
    }

    init {
        transaction(database) {
            SchemaUtils.create(Markers)
        }
    }

    suspend fun create(lat: Float, lon: Float): Long {
        return dbQuery {
            Markers.insertAndGetId {
                it[Markers.lat] = lat
                it[Markers.lon] = lon
            }
        }.value
    }

    suspend fun read(id: Long): ResultRow? {
        return dbQuery {
            Markers.selectAll().where { Users.id eq id }.singleOrNull()
        }
    }

    suspend fun readAll(): List<ResultRow> {
        return dbQuery {
            Markers.selectAll().toList()
        }
    }
 
    suspend fun update(id: Long, newLat: Float?, newLon: Float?): Boolean {
        return dbQuery {
            Markers.update({ Markers.id eq id }) { marker ->
                 newLat notNull { marker[Markers.lat] = it }
                 newLon notNull { marker[Markers.lon] = it }
            } > 0
        }
    }

    suspend fun delete(id: Long): Boolean {
        return dbQuery {
            Markers.deleteWhere { Markers.id eq id } > 0
        }
    }

}

