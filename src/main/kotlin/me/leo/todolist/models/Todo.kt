package me.leo.todolist.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Todo(
        @Id
        @GeneratedValue(strategy=GenerationType.SEQUENCE)
        var id: Long?,
        var title: String,
        var description: String,
        var done: Boolean = false
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Todo

                if (title != other.title) return false
                if (description != other.description) return false
                if (done != other.done) return false

                return true
        }

        override fun hashCode(): Int {
                var result = title.hashCode()
                result = 31 * result + description.hashCode()
                result = 31 * result + done.hashCode()
                return result
        }
}