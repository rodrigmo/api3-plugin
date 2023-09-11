package api3

import grails.gorm.transactions.Transactional
import grails.web.api.ServletAttributes
import org.grails.web.json.JSONObject
import javax.servlet.http.HttpServletRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Transactional
class LogService implements ServletAttributes {

    static defaultAction = "save"
    static allowedMethods = [save: "POST"]

    void salvarLog(HttpServletRequest request, JSONObject response) {
        String statusReq = !(response?.message || response?.errors) ? "Sucesso" : "Falha"
        String operacaoReq = "exclusão"
        if (request.method == "POST") {
            operacaoReq = "criação"
        } else if (request.method == "PUT") {
            operacaoReq = "alteração"
        }
        String controllerReq = request.servletPath

        Log novoLog = new Log()

        String dataLog
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        dataLog = LocalDateTime.now().format(formatter)
        LocalDateTime data = LocalDateTime.parse(dataLog, formatter)
        novoLog.data = data

        novoLog.descricao = statusReq + " na " + operacaoReq + " em " + controllerReq +": " + response.toString()

        novoLog.save(flush: true)
    }
}
