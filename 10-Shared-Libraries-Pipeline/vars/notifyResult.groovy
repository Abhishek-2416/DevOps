def call(String APP_DIR = '10-Shared-Libraries-Pipeline'){
    dir(APP_DIR){
        def result = currentBuild.currentResult

        def branch = env.BRANCH_NAME
        def buildNum = env.BUILD_NUMBER

        if (result == 'SUCCESS') {
        echo "✅ SUCCESS: ${branch} branch (#${buildNum})"
        } else if (result == 'FAILURE') {
            echo "❌ FAILURE: ${branch} branch (#${buildNum})"
        } else {
            echo "⚠️ Build Result: ${result} on ${branch} branch (#${buildNum})"
        }
    }
}