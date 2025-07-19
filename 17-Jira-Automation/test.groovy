// jiraUtil.groovy
// Utility methods for JIRA operations. Load in Jenkins with `def jira = load 'jiraUtil.groovy'`.

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * Creates a JIRA issue (e.g. Story) with the given fields.
 *
 * @param baseUrl     JIRA base URL (e.g. https://your-domain.atlassian.net)
 * @param username    Atlassian account email
 * @param apiToken    API token for authentication
 * @param projectKey  JIRA project key (e.g. "STPE")
 * @param issueType   Issue type name (e.g. "Story")
 * @param summary     Issue summary/title
 * @param description Issue description (multi-line OK)
 * @param assignee    (optional) user to assign
 * @param reporter    (optional) user reporting
 * @param epicLink    (optional) Epic Link value (customfield ID must match)
 * @param components  (optional) List of component names
 * @return            Map parsed from JIRA response (contains .key, .self)
 */
def createStory(
    String baseUrl,
    String username,
    String apiToken,
    String projectKey,
    String issueType,
    String summary,
    String description,
    String assignee = null,
    String reporter = null,
    String epicLink = null,
    List<String> components = []
) {
    // prepare Basic Auth header
    def authHeader = "Basic " + "${username}:${apiToken}".bytes.encodeBase64().toString()

    // build fields
    def fields = [
        project    : [ key: projectKey ],
        issuetype  : [ name: issueType ],
        summary    : summary,
        description: description
    ]
    if (assignee)   fields.assignee   = [ name: assignee ]
    if (reporter)   fields.reporter   = [ name: reporter ]
    if (epicLink)   fields['customfield_10008'] = epicLink  // adjust ID if needed
    if (components) fields.components = components.collect { [ name: it ] }

    // wrap payload
    def payload = [ fields: fields ]
    def body    = new JsonBuilder(payload).toString()

    // make HTTP call
    def conn = new URL("${baseUrl}/rest/api/2/issue").openConnection()
    conn.requestMethod          = 'POST'
    conn.doOutput               = true
    conn.setRequestProperty('Content-Type',  'application/json')
    conn.setRequestProperty('Authorization', authHeader)
    conn.outputStream.withWriter('UTF-8') { it << body }

    // handle response
    if (conn.responseCode == 201) {
        return new JsonSlurper().parseText(conn.inputStream.text) as Map
    } else {
        def err = conn.errorStream?.text ?: 'No error details'
        throw new RuntimeException("JIRA createStory failed (${conn.responseCode}): ${err}")
    }
}

// Return script binding so pipeline can call createStory()
return this
