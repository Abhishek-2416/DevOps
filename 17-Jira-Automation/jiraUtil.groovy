import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

def baseUrl = "https://abhishekalimchandani1624.atlassian.net";
def endPoint = "${baseUrl}/rest/api/2/issue"

def createJiraStory(String baseUrl,
    String username,
    String apiToken,
    String projectKey,
    String issueType,
    String summary,
    String assignee,
    String reporter,
    String epicLink,
    List<String> components,
    String description){
        def connection = new URL(endPoint).openConnection()
        connection.requestMethod = 'POST'   // we’re creating something
        connection.doOutput      = true  

        //Tell JIRA we are sending JSON
        connection.setRequestProperty('Content-Type', 'application/json')

        //Build and set Basic Auth header
        def creds = "${username}:${apiToken}".bytes.encodeBase64().toString()
        connection.setRequestProperty('Authorization', "Basic ${creds}")

        def fields = [
            project: [key: projectKey],
            issuetype: [name: issueType],
            summary: summary,
            assignee: [name: assignee],
            reporter: [name: reporter],
            customfield_10008: epicLink,
            description: description
        ]

        // def fields = [
        //     project: [key: PROJECT],
        //     issuetype: [name:['Issue Type']],
        //     summary: params.Summary,
        //     assignee: [name: params['Assignee']],
        //     reporter: [name: params['Reporter']],
        //     customfield_10008: params['Epic Link'],
        //     description: params.Description
        // ]

        //Now these fields shall go inside the JSON
        def payload = [fields: fields]

        //Seralise to JSON String
        def jsonBody = new JsonBuilder(payload).toString()

        //Send the JSON into the connection
        connection.outputStream.withWriter('UTF-8'){
            writer -> writer << jsonBody
        }

        def responseCode = connection.responseCode
        if (responseCode == 201) {
            def resp = new JsonSlurper().parseText(connection.inputStream.text)
            println "✅ Created ${resp.key}"
            return resp
        } else {
            def err = connection.errorStream?.text ?: 'No details'
            error("JIRA createStory failed (HTTP $responseCode): $err")
        }
    }
