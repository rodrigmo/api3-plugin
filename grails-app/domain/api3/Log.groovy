package api3

import java.time.LocalDate

class Log {
    LocalDate data
    String descricao

    static constraints = {
        data(blank: false, nullable: false)
        descricao(blank: false, nullable: false, maxSize: 1000)
    }

    static mapping = {
        id generator: 'increment'
        version false
        table 'logs'
    }
}
