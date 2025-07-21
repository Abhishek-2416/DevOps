@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

def jiraBaseUrl = "https://your-domain.atlassian.net"
def projectKey = "STPE"
def username = "your-email@example.com"
def apiToken = "your-api-token"

def client = new RESTClient(jiraBaseUrl)
client.auth.basic username, apiToken

println "Fetching valid issue types for project $projectKey..."

def metaResp = client.get(
    path: "/rest/api/2/issue/createmeta",
    query: [projectKeys: projectKey, expand: 'projects.issuetypes'],
    contentType: JSON
)

def issueTypes = metaResp.data.projects[0].issuetypes
println "Available Issue Types:"
issueTypes.each {
    println "- ${it.name} (ID: ${it.id})"
}
