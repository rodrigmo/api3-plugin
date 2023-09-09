package api3

import grails.gorm.transactions.Transactional
import grails.web.api.ServletAttributes

import java.time.LocalDate

@Transactional
class LogService implements ServletAttributes {

    Map salvarLog() {
        Map retorno = [:]

        Log novoLog = new Log()
        novoLog.data = new LocalDate()
        novoLog.descricao = request.JSON.descricao

        novoLog.save(flush: tue)

        retorno.success = true

        return retorno
    }
}
