@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64

def createJiraPayload(String summary, String description, String projectKey, String issueType, String assignee, String reporter) {
    def payload = [
        fields: [
            project     : [ key: projectKey ],
            summary     : summary,
            description : description,
            issuetype   : [ name: issueType ],
            assignee    : [ name: assignee ],
            reporter    : [ name: reporter ]
        ]
    ]
    return new JsonBuilder(payload).toPrettyString()
}

def createJiraTicket() {
    def summary = "Test Story Summary"
    def description = "This is a test story description"
    def projectKey = "TEST"
    def issueType = "Story"
    def assignee = "abhishekalimchandani1624"
    def reporter = "tammyreid"

    def payload = createJiraPayload(summary, description, projectKey, issueType, assignee, reporter)

    def jiraUrl = "https://abhishekalimchandani1624.atlassian.net/rest/api/2/issue"

    def email = "abhishekalimchandani1624@gmail.com"
    def apiToken = "ATATT3xFfGF0wzqLL3ZyzOeaHEZEsKFQ2FP9vLG7HOojIKNwanY9p9Jdhi2KFlJJQSX0O_FPjYuV61cmLOCwWPQyNhBPNRj5eUJpfolUWJGKWHls6dGjq8pFkzmxGfzDjBL9tne2NPIAlvRHOe3i6cbWoaXlA41iO9ce9klC95eyRoRwgUorxj8=CA21824C"
    def authString = "${email}:${apiToken}".bytes.encodeBase64().toString()

    def connection = new URL(jiraUrl).openConnection() as HttpURLConnection
    connection.setRequestMethod("POST")
    connection.setRequestProperty("Authorization", "Basic ${authString}")
    connection.setRequestProperty("Content-Type", "application/json")
    connection.setDoOutput(true)

    connection.outputStream.withWriter("UTF-8") { it.write(payload) }

    def responseCode = connection.responseCode
    println "Response Code: $responseCode"

    if (responseCode == 201) {
        def response = connection.inputStream.text
        println "Ticket created: $response"
    } else {
        def errorResponse = connection.errorStream?.text
        println "Failed to create ticket. Response Code: $responseCode"
        println "Error: $errorResponse"
    }
}

createJiraTicket()
