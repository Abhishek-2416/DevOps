import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Creates a JIRA Story and returns the parsed response.
 *
 * @param baseUrl     your JIRA root URL
 * @param username    your Atlassian account email
 * @param apiToken    your JIRA API token
 * @param projectKey  JIRA project key (e.g. "STPE")
 * @param issueType   Issue type name (e.g. "Story")
 * @param summary     Issue summary/title
 * @param assignee    Username to assign to
 * @param reporter    Username reporting the issue
 * @param epicLink    Epic key (goes into customfield_10008)
 * @param description Issue description
 * @return            Map with at least `.key` and `.self`
 */
def createJiraStory(String baseUrl,
                    String username,
                    String apiToken,
                    String projectKey,
                    String issueType,
                    String summary,
                    String assignee,
                    String reporter,
                    String epicLink,
                    String description) {
    def endpoint   = "${baseUrl}/rest/api/2/issue"
    def conn       = new URL(endpoint).openConnection()
    conn.requestMethod = 'POST'
    conn.doOutput      = true

    // Headers
    conn.setRequestProperty('Content-Type', 'application/json')
    def creds = "${username}:${apiToken}".bytes.encodeBase64().toString()
    conn.setRequestProperty('Authorization', "Basic ${creds}")

    // Build the JSON payload
    def fields = [
      project          : [ key: projectKey ],
      issuetype        : [ name: issueType ],
      summary          : summary,
      description      : description,
      assignee         : [ name: assignee ],
      reporter         : [ name: reporter ],
      customfield_10008: epicLink
    ]
    def payload  = [ fields: fields ]
    def jsonBody = new JsonBuilder(payload).toString()

    // Send it
    conn.outputStream.withWriter('UTF-8') { it << jsonBody }

    // Handle response
    def code = conn.responseCode
    if (code == 201) {
        return new JsonSlurper().parseText(conn.inputStream.text)
    } else {
        def err = conn.errorStream?.text ?: 'No details'
        throw new RuntimeException("JIRA createStory failed (HTTP $code): $err")
    }
}

// so that `load 'jiraUtil.groovy'` returns this binding
return this
