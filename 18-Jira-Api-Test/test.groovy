def url = "https://abhishekalimchandani1624.atlassian.net/rest/api/2/issue"
def authHeader = "abhishekalimchandani1624@gmail.com:ATATT3xFfGF0wzqLL3ZyzOeaHEZEsKFQ2FP9vLG7HOojIKNwanY9p9Jdhi2KFlJJQSX0O_FPjYuV61cmLOCwWPQyNhBPNRj5eUJpfolUWJGKWHls6dGjq8pFkzmxGfzDjBL9tne2NPIAlvRHOe3i6cbWoaXlA41iO9ce9klC95eyRoRwgUorxj8=CA21824C"  // pre-encoded

def requestBody = '''
{
    "fields": {
        "project": {
            "key": "DEMO"
        },
        "summary": "Groovy test ticket",
        "description": "Created using curl and Groovy",
        "issuetype": {
            "name": "Story"
        }
    }
}
'''

def response = httpRequestJson("POST", url, authHeader, requestBody)
println "Status: ${response.status}"
println "Response: ${response.content}"
