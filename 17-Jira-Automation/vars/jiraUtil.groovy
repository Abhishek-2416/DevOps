// vars/jiraUtil.groovy

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
    return new groovy.json.JsonBuilder(payload).toPrettyString()
}

def call(httpRequestType, url, authentication = 'jira-api-token-id', body = null) {
    try {
        def response = httpRequest(
            ignoreSslErrors: true,
            authentication: authentication,
            acceptType: 'APPLICATION_JSON',
            contentType: 'APPLICATION_JSON',
            httpMode: httpRequestType,
            requestBody: body,
            url: url
        )
        return response
    } catch (err) {
        error "Failed to create JIRA ticket: ${err}"
    }
}

def createJiraTicket(String summary, String description, String projectKey, String issueType, String assignee, String reporter) {
    def jiraUrl = "https://abhishekalimchandani1624.atlassian.net/rest/api/2/issue"
    def payload = createJiraPayload(summary, description, projectKey, issueType, assignee, reporter)

    def response = call('POST', jiraUrl, 'jira-api-token-id', payload)

    echo "JIRA ticket created successfully: ${response.content}"
}
