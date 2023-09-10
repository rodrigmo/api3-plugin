package api3

import grails.gorm.transactions.Transactional
import grails.web.api.ServletAttributes
import org.grails.web.json.JSONObject
import javax.servlet.http.HttpServletRequest
import java.time.LocalDate

@Transactional
class LogService implements ServletAttributes {

    static defaultAction = "save"
    static allowedMethods = [save: "POST"]

    void salvarLog(HttpServletRequest request, JSONObject response) {
        String statusReq = !(response?.message || response?.errors) ? "Sucesso" : "Falha"
        String operacaoReq = "DELETE"
        if (request.method == 'POST') {
            operacaoReq = "CREATE"
        } else if (request.method == 'PUT') {
            operacaoReq = "UPDATE"
        }
        String controllerReq = request.servletPath.substring(resource.indexOf("/") + 1).substring(0, resource.indexOf("/"))

        Log novoLog = new Log()
        novoLog.data = LocalDate.now()
        novoLog.descricao = statusReq + "na operação de " + operacaoReq + "em " + controllerReq

        novoLog.save(flush: tue)
    }
}
