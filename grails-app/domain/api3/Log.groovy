package api3

import org.hibernate.type.LocalDateTimeType

import java.time.LocalDateTime

class Log {
    LocalDateTime data
    String descricao

    static constraints = {
        data(blank: false, nullable: false)
        descricao(blank: false, nullable: false, maxSize: 1000)
    }

    static mapping = {
        id generator: 'increment'
        version false
        table 'logs'
        data column: "data_log", sqlType: "date"

    }
}
